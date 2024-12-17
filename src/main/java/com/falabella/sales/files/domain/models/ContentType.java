package com.falabella.sales.files.domain.models;

import lombok.Getter;

@Getter
public enum ContentType {
    IMAGE_JPEG("image/jpeg"),
    IMAGE_JPG("image/jpg"),
    IMAGE_PNG("image/png"),
    APPLICATION_PDF("application/pdf"),
    TEXT_PLAIN("text/plain"),
    APPLICATION_JSON("application/json"),
    APPLICATION_MSWORD("application/msword"),
    APPLICATION_ZIP("application/zip"),
    APPLICATION_X_DIRECTORY("application/x-directory; charset=UTF-8");
    private final String mimeType;
    ContentType(String mimeType) {
        this.mimeType = mimeType;
    }
    public static ContentType fromString(String mimeType) {
        for (ContentType type : ContentType.values()) {
            if (type.getMimeType().equalsIgnoreCase(mimeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown content type: " + mimeType);
    }
}
