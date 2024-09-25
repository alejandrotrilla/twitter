package ar.com.uala.twitter;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class TwitterApplication {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}