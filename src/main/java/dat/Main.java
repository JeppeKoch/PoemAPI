package dat;

import dat.config.HibernateConfig;
import dat.controllers.PoemController;
import dat.daos.PoemDAO;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import dat.entities.Type;
import dat.services.PoemService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var poemApp = Javalin.create((config) -> {
            config.router.contextPath = "/api/poem";
            config.bundledPlugins.enableRouteOverview(("/routes"));
        });


        PoemDAO poemDAO = new PoemDAO();

        // Create 5 different poems in various styles
        PoemDTO haiku = new PoemDTO();
        haiku.setPoem("An old silent pond,\nA frog jumps into the pond—\nSplash! Silence again.");
        haiku.setTitle("The Old Pond");
        haiku.setAuthor("Matsuo Basho");
        haiku.setType(Type.HAIKU);

        PoemDTO sonnet = new PoemDTO();
        sonnet.setPoem("Shall I compare thee to a summer’s day?\nThou art more lovely and more temperate.");
        sonnet.setTitle("Sonnet 18");
        sonnet.setAuthor("William Shakespeare");
        sonnet.setType(Type.SLAM);

        PoemDTO villanelle = new PoemDTO();
        villanelle.setPoem("Do not go gentle into that good night,\nOld age should burn and rave at close of day;\nRage, rage against the dying of the light.");
        villanelle.setTitle("Do Not Go Gentle into That Good Night");
        villanelle.setAuthor("Dylan Thomas");
        villanelle.setType(Type.VILLANELLE);

        PoemDTO elegy = new PoemDTO();
        elegy.setPoem("O Captain! my Captain! our fearful trip is done,\nThe ship has weather’d every rack, the prize we sought is won.");
        elegy.setTitle("O Captain! My Captain!");
        elegy.setAuthor("Walt Whitman");
        elegy.setType(Type.ELEGY);

        PoemDTO freeVerse = new PoemDTO();
        freeVerse.setPoem("The fog comes\non little cat feet.\nIt sits looking\nover harbor and city\non silent haunches\nand then moves on.");
        freeVerse.setTitle("Fog");
        freeVerse.setAuthor("Carl Sandburg");
        freeVerse.setType(Type.FREE_VERSE);

        // Save the poems using the DAO
        poemDAO.create(haiku);
        poemDAO.create(sonnet);
        poemDAO.create(villanelle);
        poemDAO.create(elegy);
        poemDAO.create(freeVerse);







        poemApp.get("/", ctx -> ctx.json(poemDAO.findAll()));
        poemApp.post("/poems", PoemController::create);
        poemApp.get("/poems/{id}", PoemController::getById);





        poemApp.start(7008);

        }
    }
