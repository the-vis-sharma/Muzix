package in.stackroute.muzix.service;

import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrackDummyService {

    private static List<Track> trackList = new ArrayList<>();

    @Autowired
    private TrackRepository trackRepository;

    public ResponseEntity addTrack(Track track) {
        trackList.add(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track added successfully.");
        map.put("status", HttpStatus.CREATED);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    public ResponseEntity getAllTrack() {
        Map<String, Object> map = new TreeMap<>();
        map.put("message", (trackList.size()==0) ? "No records found" : "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", trackList);
        map.put("count", trackList.size());
        return new ResponseEntity(map, HttpStatus.OK);
    }

    public ResponseEntity getTrackByName(String title) {
        Map<String, Object> map = new TreeMap<>();
        map.put("message", (trackList.size()==0) ? "No records found" : "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", trackList);
        map.put("count", trackList.size());
        return new ResponseEntity(map, HttpStatus.OK);
    }

    public ResponseEntity getTrackById(int id) {
        Track track = findById(id);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", track);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    public ResponseEntity updateTrack(int id, Track trackDetail) {
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

    public ResponseEntity deleteTrack(int id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        trackRepository.delete(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track detail has been delete for Id: " + id);
        map.put("status", HttpStatus.OK);
        return new ResponseEntity(map, HttpStatus.OK);
    }

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


    public Track findById(int id) {
        for(Track track : trackList) {
            if(track.getTrackId()==id) {
                return track;
            }
        }
        return null;
    }

}
