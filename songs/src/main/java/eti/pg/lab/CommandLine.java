package eti.pg.lab;

import eti.pg.lab.author.entity.Author;
import eti.pg.lab.author.service.AuthorService;
import eti.pg.lab.song.entity.Song;
import eti.pg.lab.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private SongService songService;
    private AuthorService authorService;

    @Autowired
    public CommandLine(SongService songService, AuthorService authorService) {
        this.songService = songService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        Boolean end = false;

        //songService.findAll().forEach(System.out::println);
        System.out.println("Welcome to our music library");
        //songService.find("admin", "adminadmin").ifPresent(System.out::println);

        while (!end){
            System.out.print("Command: ");
            cmd = scanner.nextLine();
            switch (cmd) {
                case "help":
                    System.out.println("add song - to add song");
                    System.out.println("delete song - to delete song");
                    System.out.println("list songs - to list all songs");
                    System.out.println("list authors - to list all authors");
                    break;
                case "quit": end = true; break;
                case "add song":

                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Song_id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Author_id: ");
                    int aut = Integer.parseInt(scanner.nextLine());
                    //System.out.println(aut);
                    System.out.print("Time: ");
                    int time = Integer.parseInt(scanner.nextLine());

                    Optional<Author> author = authorService.find(aut);
                    if (author.isEmpty()) {
                        break;
                    }
                    //author = authorService.find(aut);
                    author.ifPresent(autor -> {songService.create(new Song(id, title, time, LocalDate.of(2023, 1, 11), autor));});
                    break;
                case "delete song":
                    System.out.print("Id: ");
                    int song_id = Integer.parseInt(scanner.nextLine());
                    songService.delete(song_id);
                    break;
                case "add author":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Author_id: ");
                    int aut_id = Integer.parseInt(scanner.nextLine());
                    LocalDate localDate = LocalDate.of(2023, 1, 11);
                    authorService.create(new Author(aut_id, name, localDate));
                    break;
                case "list songs": songService.findAll().forEach(System.out::println); break;
                case "list authors": authorService.findAll().forEach(System.out::println); break;
            }
        }

    }

}
