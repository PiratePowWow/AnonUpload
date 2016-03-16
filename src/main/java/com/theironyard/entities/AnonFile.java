package com.theironyard.entities;

import javax.persistence.*;
import java.sql.Timestamp;

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
    boolean isPerm;
    @Column(nullable = false)
    Timestamp timestamp;
    String comment;

    public AnonFile(String filename, String originalFilename, boolean isPerm, Timestamp timestamp, String comment) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.isPerm = isPerm;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    public AnonFile() {
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

    public boolean isPerm() {
        return isPerm;
    }

    public void setPerm(boolean perm) {
        isPerm = perm;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
