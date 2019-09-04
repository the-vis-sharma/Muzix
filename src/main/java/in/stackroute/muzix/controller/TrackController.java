package in.stackroute.muzix.controller;

import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import in.stackroute.muzix.service.TrackService;
import in.stackroute.muzix.service.TrackServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    @Autowired
    private TrackServiceInterface trackService;

    // endpoint: api/v1/tracks method: post
    @PostMapping("tracks")
    public ResponseEntity addTrack(@RequestBody Track track) {
        Map<String, Object> map = trackService.addTrack(track);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    // endpoint: api/v1/tracks method: get
    @GetMapping("tracks")
    public ResponseEntity getAllTrack(@RequestParam(value = "title", required = false) String title) {
        if(title != null) {
            return new ResponseEntity(trackService.getTrackByName(title), HttpStatus.OK);
        }
        return new ResponseEntity(trackService.getAllTrack(), HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: get
    @GetMapping("tracks/{id}")
    public ResponseEntity getTrackById(@PathVariable("id") int id) {
        return new ResponseEntity(trackService.getTrackById(id), HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: put
    @PutMapping("tracks/{id}")
    public ResponseEntity updateTrack(@PathVariable("id") int id, @RequestBody  Track trackDetail) {
        return new ResponseEntity(trackService.updateTrack(id, trackDetail), HttpStatus.OK);
    }

    // endpoint: api/v1/tracks/id method: delete
    @DeleteMapping("tracks/{id}")
    public ResponseEntity deleteTrack(@PathVariable("id") int id) {
        return new ResponseEntity(trackService.deleteTrack(id), HttpStatus.OK);
    }

    @GetMapping("tracks/dummy")
    public ResponseEntity createDummy() {
        return new ResponseEntity(trackService.createDummy(), HttpStatus.OK);
    }
}
