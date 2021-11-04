package br.com.crisaltmann;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.IOException;

public class AvroSerialize {

    private static final String PATH = "users.avro";

    public static void readUser() {
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        try (DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(PATH), userDatumReader)) {
            User user = null;
            while (dataFileReader.hasNext()) {
                user = dataFileReader.next(user);
                System.out.print(" USER -> ");
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUser(User user) {
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        try {
            dataFileWriter.create(user.getSchema(), new File(PATH));
            dataFileWriter.append(user);
            dataFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(dataFileWriter);
        }
    }

}
