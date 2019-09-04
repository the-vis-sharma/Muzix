package in.stackroute.muzix.service;

import in.stackroute.muzix.exception.TrackAlreadyExistsException;
import in.stackroute.muzix.exception.TrackNotFoundException;
import in.stackroute.muzix.model.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface TrackServiceInterface {

    Map<String, Object> addTrack(Track track);
    Map<String, Object> getAllTrack();
    Map<String, Object> getTrackByName(String title);
    Map<String, Object> getTrackById(int id);
    Map<String, Object> updateTrack(int id, Track trackDetail);
    Map<String, Object> deleteTrack(int id);
    Map<String, Object> createDummy();

}
