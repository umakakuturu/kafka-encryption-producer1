package com.example.kafkaencryptionproducer;

import org.apache.avro.reflect.Nullable;

public class YourAvroRecordClass {

    private String ssn;  // Add other fields as needed

    // Constructors, getters, setters, etc.

    @Nullable
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
