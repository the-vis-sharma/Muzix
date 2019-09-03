package in.stackroute.muzix;

import in.stackroute.muzix.model.Track;
import in.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class MuzixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzixApplication.class, args);
    }

}
