package platform.dto;

import java.util.UUID;

import platform.exception.WrongArgumentException;

public class CodeSnippetIdDTO {

    private UUID id;

    public CodeSnippetIdDTO() {
        super();
    }

    public CodeSnippetIdDTO(UUID id) {
        this.id = id;
    }

    public CodeSnippetIdDTO(String id) {
        this.id = getUUIDFromString(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = getUUIDFromString(id);
    }

    private UUID getUUIDFromString(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException("Invalid code snippet id: " + id);
        }
        return uuid;
    }

}
