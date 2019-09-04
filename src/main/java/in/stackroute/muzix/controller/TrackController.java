package in.stackroute.muzix.controller;

import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import in.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    @Autowired
    private TrackService trackService;

    // endpoint: api/v1/tracks method: post
    @PostMapping("tracks")
    public ResponseEntity addTrack(@RequestBody Track track) {
        return trackService.addTrack(track);
    }

    // endpoint: api/v1/tracks method: get
    @GetMapping("tracks")
    public ResponseEntity getAllTrack(@RequestParam(value = "title", required = false) String title) {
        if(title != null) {
            return trackService.getTrackByName(title);
        }
        return trackService.getAllTrack();
    }

    // endpoint: api/v1/tracks/id method: get
    @GetMapping("tracks/{id}")
    public ResponseEntity getTrackById(@PathVariable("id") int id) {
        return trackService.getTrackById(id);
    }

    // endpoint: api/v1/tracks/id method: put
    @PutMapping("tracks/{id}")
    public ResponseEntity updateTrack(@PathVariable("id") int id, @RequestBody  Track trackDetail) {
        return trackService.updateTrack(id, trackDetail);
    }

    // endpoint: api/v1/tracks/id method: delete
    @DeleteMapping("tracks/{id}")
    public ResponseEntity deleteTrack(@PathVariable("id") int id) {
        return trackService.deleteTrack(id);
    }

    @GetMapping("tracks/dummy")
    public ResponseEntity createDummy() {
        return trackService.createDummy();
    }
}
