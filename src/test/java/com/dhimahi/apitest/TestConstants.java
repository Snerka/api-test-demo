package com.dhimahi.apitest;

public class TestConstants {
    public static final String BASE_URI = "https://api.practicesoftwaretesting.com"; // Base URI for the API
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2FwaS5wcmFjdGljZXNvZnR3YXJldGVzdGluZy5jb20vdXNlcnMvbG9naW4iLCJpYXQiOjE3Mjc5OTY4NzAsImV4cCI6MTcyNzk5NzE3MCwibmJmIjoxNzI3OTk2ODcwLCJqdGkiOiJRUUExWXg0c1l1OWE5R1lrIiwic3ViIjoiMDFKOUE5U0Y0V0ZORTRIRU02MUdYUTAwRzgiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3Iiwicm9sZSI6ImFkbWluIn0.StmJW5wNnXuNoiCRI9mLJpqD5SN7r2P7QDR1nAju1oU"; // API token

    // JSON values Constants
    public static final String NEW_BRAND_NAME = "DhiMahi Giga Chad"; // New brand name
    public static final String NEW_BRAND_SLUG = "dhi-mahi-giga-chad"; // New brand slug
    public static final String UPDATED_BRAND_NAME = "Updated Giga Chad"; // Updated brand name
    public static final String UPDATED_BRAND_SLUG = "updated-giga-chad"; // Updated brand slug

    // JSON Body Templates
    public static final String NEW_BRAND_JSON = String.format("{ \"name\": \"%s\", \"slug\": \"%s\" }", NEW_BRAND_NAME, NEW_BRAND_SLUG);
    public static final String UPDATED_BRAND_JSON = String.format("{ \"name\": \"%s\", \"slug\": \"%s\" }", UPDATED_BRAND_NAME, UPDATED_BRAND_SLUG);
}

