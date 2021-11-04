package br.com.crisaltmann;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class AvroSerializeWithoutClass {

    private static final String PATH = "users-schemaless.avro";

    public static void readUser() {
        Schema schema = readSchema();
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File(PATH), datumReader)) {
            GenericRecord user = null;
            while (dataFileReader.hasNext()) {
                user = dataFileReader.next();
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUser() {
        Schema schema = readSchema();
        GenericRecord user = new GenericData.Record(schema);
        user.put("name", "Fulano da Silva");
        user.put("favorite_number", 7);
        user.put("favorite_color", " green");

        DatumWriter<GenericRecord> userDatumWriter = new GenericDatumWriter<GenericRecord>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(userDatumWriter)) {
            dataFileWriter.create(schema, new File(PATH));
            dataFileWriter.append(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Schema readSchema() {
        String content = null;
        try {
            content = Files.readString(Paths.get(AvroSerializeWithoutClass.class.getResource("/schema.avsc").toURI()), Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new Schema.Parser().parse(content);
    }
}
