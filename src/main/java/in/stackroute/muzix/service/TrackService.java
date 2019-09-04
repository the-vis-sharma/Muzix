package in.stackroute.muzix.service;

import in.stackroute.muzix.exception.TrackAlreadyExistsException;
import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Primary
public class TrackService implements TrackServiceInterface {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Map<String, Object>  addTrack(Track track) {
        if(trackRepository.findByTitle(track.getTitle()).size()>0) {
            throw new TrackAlreadyExistsException("Track", "Title", track.getTitle());
        }
        trackRepository.save(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track added successfully.");
        map.put("status", HttpStatus.CREATED);
        map.put("data", track);
        return map;
    }

    public Map<String, Object>  getAllTrack() {
        List<Track> trackList = trackRepository.findAll();

        Map<String, Object> map = new TreeMap<>();
        map.put("message", (trackList.size()==0) ? "No records found" : "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", trackList);
        map.put("count", trackList.size());
        return map;
    }

    public Map<String, Object>  getTrackByName(String title) {
        List<Track> trackList = new ArrayList<>();
        if(!title.isEmpty()) {
            trackList = trackRepository.findByTitle(title);
        }

        Map<String, Object> map = new TreeMap<>();
        map.put("message", (trackList.size()==0) ? "No records found" : "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", trackList);
        map.put("count", trackList.size());
        return map;
    }

    public Map<String, Object> getTrackById(int id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "data loaded successfully.");
        map.put("status", HttpStatus.OK);
        map.put("data", track);
        return map;
    }

    public Map<String, Object>  updateTrack(int id, Track trackDetail) {
        System.out.println("track detail" + trackDetail);
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        track.setTitle(trackDetail.getTitle());
        track.setSinger(trackDetail.getSinger());
        track.setGenre(trackDetail.getGenre());
        trackRepository.save(track);

        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track detail updated successfully with Id: " + id);
        map.put("status", HttpStatus.OK);
        map.put("data", track);
        return map;
    }

    public Map<String, Object>  deleteTrack(int id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException("Track", "id", id));
        trackRepository.delete(track);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Track detail has been delete for Id: " + id);
        map.put("status", HttpStatus.OK);
        map.put("data", track);
        return map;
    }

    public Map<String, Object>  createDummy() {
        List<Track> list = new ArrayList<>();
        for(int i=1; i<=25; i++) {
            list.add(new Track("Title" + i, "Singer" + i, "Genre" + i));
        }
        trackRepository.saveAll(list);
        Map<String, Object> map = new TreeMap<>();
        map.put("message", "Dummy Tracks have been created.");
        map.put("status", HttpStatus.OK);
        return map;
    }

}
