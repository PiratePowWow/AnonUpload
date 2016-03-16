package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by PiratePowWow on 3/16/16.
 */
@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;
    @Column(nullable = false)
    String filename;
    @Column(nullable = false)
    String originalFilename;

    public AnonFile(int id, String filename, String originalFilename) {
        this.id = id;
        this.filename = filename;
        this.originalFilename = originalFilename;
    }

    public AnonFile(String name, String originalFilename) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
