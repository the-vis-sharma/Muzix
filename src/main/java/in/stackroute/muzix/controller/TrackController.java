package in.stackroute.muzix.controller;

import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    // endpoint: api/v1/tracks method: post
    @PostMapping("tracks")
    public ResponseEntity addTrack(@RequestBody Track track) {
        trackRepository.save(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track added successfully.");
        map.put("status", HttpStatus.CREATED);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    // endpoint: api/v1/tracks method: get
    @GetMapping("tracks")
    public ResponseEntity getAllTrack(@RequestParam(value = "title", required = false) String title) {
        List<Track> trackList;
        if(title != null && !title.isEmpty()) {
            trackList = trackRepository.findByTitle(title);
        }
        else {
            trackList = trackRepository.findAll();
        }

        Map<String, Object> map = new TreeMap<>();
        map.put("message", (trackList.size()==0) ? "No records found" : "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", trackList);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: get
    @GetMapping("tracks/{id}")
    public ResponseEntity getTrackById(@PathVariable("id") int id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", track);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: put
    @PutMapping("tracks/{id}")
    public ResponseEntity updateTrack(@PathVariable("id") int id, @RequestBody  Track trackDetail) {
        System.out.println("track detail" + trackDetail);
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        track.setTitle(trackDetail.getTitle());
        track.setSinger(trackDetail.getSinger());
        track.setGenre(trackDetail.getGenre());
        trackRepository.save(track);

        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track detail updated successfully with Id: " + id);
        map.put("status", HttpStatus.OK);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: delete
    @DeleteMapping("tracks/{id}")
    public ResponseEntity deleteTrack(@PathVariable("id") int id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        trackRepository.delete(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track detail has been delete for Id: " + id);
        map.put("status", HttpStatus.OK);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping("tracks/dummy")
    public ResponseEntity createDummy() {
        List<Track> list = new ArrayList<>();
        for(int i=1; i<=25; i++) {
            list.add(new Track("Title" + i, "Singer" + i, "Genre" + i));
        }
        trackRepository.saveAll(list);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Dummy Tracks have been created.");
        map.put("status", HttpStatus.OK);
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
