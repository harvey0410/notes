package com.api.note;

public class Note {
    public int id = 0;

    public String title = "";
    public String description = "";

    public Note( int id, String title, String description ) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId( int id ) {
        this.id = id;
    }
    
    public void setTitle( String title ) {
        this.title = title;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
