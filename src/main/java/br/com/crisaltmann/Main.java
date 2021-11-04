package br.com.crisaltmann;


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

        AvroSerializeWithoutClass.writeUser();
        AvroSerializeWithoutClass.readUser();
    }


}
