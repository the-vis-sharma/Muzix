package in.stackroute.muzix;

import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import in.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class MuzixApplication implements CommandLineRunner {

    @Value(value = "${spring.profiles.active}")
    private String profile;

    public static void main(String[] args) {
        SpringApplication.run(MuzixApplication.class, args);
    }

    @Autowired
    private TrackService trackService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("profile: " + profile);
        if(profile.equals("dev")) {
            trackService.createDummy();
        }
    }
}
