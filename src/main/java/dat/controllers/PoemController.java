package dat.controllers;


import dat.daos.PoemDAO;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class PoemController {

    public static List<PoemDTO> poems = new ArrayList<>();
    public static PoemDAO poemDAO = new PoemDAO();

    public static void create(Context ctx){
        PoemDTO poem = ctx.bodyAsClass(PoemDTO.class);
        if (poem != null){
            poemDAO.create(poem);
            ctx.json(poem, PoemDTO.class);
            poems.add(poem);
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public static void getAll(Context ctx){
        if(poems != null && !poems.isEmpty()){
            List<PoemDTO> poemList = poemDAO.findAll();
            ctx.json(poemList);
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

   public static void getById(Context ctx){
        Long id = Long.parseLong(ctx.pathParam("id"));
        PoemDTO poem = poemDAO.findById(id);
        if(poem != null){
            ctx.json(poem, Poem.class);
            ctx.status(200);
        }else {
            ctx.status(400);
        }
    }

    public static void update(Context ctx){
        String id = ctx.pathParam("id");
        Poem poem = ctx.bodyAsClass(Poem.class);
        if(poem != null){

        } else {
            ctx.status(400);
        }
    }
}
