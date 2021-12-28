package com.example.cinema.service;

import com.example.cinema.models.*;
import com.example.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class CinemaInitServiceImpl implements ICinemaInitService {
   @Autowired
    private VilleRepository villeRepository;
 @Autowired
 private CinemaRepository cinemaRepository;
 @Autowired
 private SalleRepository salleRepository;
 @Autowired
 private PlaceRepository placeRepository;
 @Autowired
 private SeanceRepository seanceRepository;
 @Autowired
 private FilmRepository filmRepository;
 @Autowired
 private ProjectionRepository projectionRepository;
 @Autowired
 private TicketRepository ticketRepository;
 @Autowired
 private CategorieRepository categorieRepository;
    @Override
    public void initVilles() {
       Stream.of("Sousse","Mahdia","Sfax","Tunis").forEach(nameVille->{
           Ville ville=new Ville();
                ville.setName(nameVille);
                System.out.println(ville);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
     villeRepository.findAll().forEach(v->{
      Stream.of("Mega Rama","IMax","Fnoun","CHAHRAZAD","DAOULIZ").forEach(nameCineme->{
       Cinema cinema=new Cinema();
       cinema.setName(nameCineme);
       cinema.setNombreSalles(3+(int)(Math.random()*7));
       cinema.setVille(v);
       cinemaRepository.save(cinema);
      });

     });


    }

    @Override
    public void initSalles() {
cinemaRepository.findAll().forEach(cinema->{
 for(int i=0;i<cinema.getNombreSalles();i++){
  Salle salle=new Salle();
  salle.setName("Salle"+(i+1));
  salle.setCinema(cinema);
  salle.setNombrePlace(20+(int)(Math.random()*10));
  salleRepository.save(salle);

 }

});
    }
 @Override
 public void initPlaces() {
     salleRepository.findAll().forEach(salle->{
      for(int i=0;i<salle.getNombrePlace();i++){
       Place place=new Place();
       place.setNumero(i+1);
       place.setSalle(salle);
       placeRepository.save(place);
      }
     });

 }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
            Seance seance=new Seance();
            try{
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);

            }catch (ParseException e) {

            }
        });

    }

    @Override
    public void initCategorie() {
        Stream.of("Historique","Actions","Fiction","Drama").forEach(cat->{
            Categorie categorie=new Categorie();

            categorie.setName(cat);
            categorieRepository.save(categorie);
        });

    }
    @Override
    public void initFilms() {
        List <Categorie> categories=categorieRepository.findAll();
        double[] durees=new double[]{1,1.5,2.5,3};
        Stream.of("Game of thrones","Seigneur des anneaux","Spider man","IRON Man","Cat Women").
                forEach(titreFilm->{
            Film film=new Film();
            film.setTitre(titreFilm);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(titreFilm.replaceAll("",""));
            film.setCategorie(categories.get(new Random().nextInt(((List<?>) categories).size())));
            filmRepository.save(film);

        });

    }

    @Override
    public void initProjections() {
        double [] prices=new double []{30,40,50,60,100,120};
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema->{
                cinema.getSalles().forEach(salle->{
                    filmRepository.findAll().forEach(film->{
                         seanceRepository.findAll()
                                 .forEach(seance->{
                        Projection projection=new Projection();
                        projection.setDateProjection(new Date());
                        projection.setFilm(film);
                        projection.setPrix(prices[new Random().nextInt(prices.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionRepository.save(projection);


                                 });
                    });
                });
            });

        });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
             p.getSalle().getPlaces().forEach(place->{
                 Ticket ticket =new Ticket();
                 ticket.setPalace(place);
                 ticket.setPrix(p.getPrix());
                 ticket.setProjection(p);
                 ticket.setReserve(false);
                 ticketRepository.save(ticket);
             });
        });

    }
}
