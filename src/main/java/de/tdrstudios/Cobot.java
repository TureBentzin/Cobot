package de.tdrstudios;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ture Bentzin
 * @since 30-06-2025
 */

public class Cobot {
    @NotNull
    private static final Logger log = LoggerFactory.getLogger(Cobot.class);

    public static void main(String[] args) {
        log.info("Starting Cobot...");
        JDA jda = JDABuilder.createDefault(System.getenv("TOKEN")).build();

        jda.addEventListener(new MessageListener());

        try {
            jda.awaitReady();
            log.info("Cobot is ready!");
        } catch (InterruptedException e) {
            log.error("Failed to initialize JDA", e);
            throw new RuntimeException(e);
        }
    }

}