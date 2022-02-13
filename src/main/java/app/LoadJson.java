package app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

    public class LoadJson {

        public static ArrayList<Event> getEvents(String jsonPath) {
            JSONParser parser = new JSONParser();
            ArrayList<Event> events = new ArrayList<>();
            try {
                JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(jsonPath));
                for (Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;
                    events.add(new Event(
                            Math.toIntExact((Long) jsonObject.get("variant")),
                            Math.toIntExact((Long) jsonObject.get("case_key")),
                            new ArrayList<>(Arrays.asList(((String) jsonObject.get("cas")).split("-"))))
                    );
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return events;
        }

    }


