package br.com.crisaltmann;


import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        System.out.println("Teste");

        User user = User.newBuilder()
                .setName("Cristiano Altmann")
                .setFavoriteColor("Blue")
                .setFavoriteNumber(8)
                .build();

        AvroSerialize.writeUser(user);
        AvroSerialize.readUser();
    }


}
