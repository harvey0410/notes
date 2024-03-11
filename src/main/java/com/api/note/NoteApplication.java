package com.api.note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NoteApplication {

	// Map to store notes with their IDs
	public Map<Integer, Note> notes = new HashMap<>();
	// To keep track of the last inserted ID
	private int lastInsertId = -1;

	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

	/**
     * POST /notes: Create a new note.
     * 
     * @param title The title of the note
     * @param description The description of the note
     * @return Response containing status and created note
     */
	@PostMapping("/notes")
	private Response postNote( @RequestParam( value = "title") String title, @RequestParam( value = "description" ) String description ) {
		if( title == null || title.isEmpty() ) {
			return new Response( 400, "Title cannot be empty", null );
		}
	
		if( description == null || description.isEmpty() ) {
			return new Response( 400, "Description cannot be empty", null );
		}

		lastInsertId ++;
		notes.put(lastInsertId, new Note( lastInsertId, title, description) );
		Note note = notes.get(lastInsertId);
		
		return new Response( 200, "Note: " + lastInsertId + " has been successfully added!", note);
	}

	/**
     * GET /notes: Retrieve all notes.
     * 
     * @return Response containing status and list of all notes
     */
	@GetMapping("/notes")
	private Response getNotes() {
		List<Note> values = new ArrayList<Note>(notes.values());
		return new Response( 200, "All Notes", values );
	}

	/**
     * GET /notes/:id: Retrieve a specific note by ID.
     * 
     * @param id The ID of the note to retrieve
     * @return Response containing status and retrieved note
     */
	@GetMapping("/notes/{id}")
	private Response getNote(@PathVariable("id") int id) {
		if( !notes.containsKey(id) ) {
			return new Response( 404, "Note not found", null );
		}
		return new Response( 200, "Note: " + id, notes.get(id) );
	}

	/**
     * PUT /notes/:id: Update a specific note.
     * 
     * @param id The ID of the note to update
     * @param title The new title for the note
     * @param description The new description for the note
     * @return Response containing status and updated note
     */
	@PutMapping("/notes/{id}")
	private Response updateNote( @PathVariable( "id" ) int id, @RequestParam( value = "title" ) String title, @RequestParam( value = "description" ) String description ) {
		if( !notes.containsKey(id) ) {
			return new Response( 404, "Unable to update note. Note not found", null );
		}

		if( title == null || title.isEmpty() ) {
			return new Response( 400, "Title cannot be empty", null );
		}
	
		if( description == null || description.isEmpty() ) {
			return new Response( 400, "Description cannot be empty", null );
		}

		Note note = notes.get(id);
		note.setTitle(title);
		note.setDescription(description);

		return new Response( 200, "Note: " + id + " has been updated successfully!", note );
	}
	
    /**
     * DELETE /notes/:id: Delete a specific note.
     * 
     * @param id The ID of the note to delete
     * @return Response containing status
     */
	@DeleteMapping("/notes/{id}")
	private Response deleteNote(@PathVariable("id") int id) {
		if( !notes.containsKey(id) ) {
			return new Response( 404, "Unable to delete note. Note not found", null );
		}
		
		notes.remove(id);

		return new Response( 200, "Note: " + id + " has been deleted!", null );
	}
}
