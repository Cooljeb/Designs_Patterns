package fr.diginamic.hello.controleurs;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.dto.VilleMapper;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exceptions.DocumentException;
import fr.diginamic.hello.exceptions.VillesExceptions;
import fr.diginamic.hello.repositories.VilleRepository;
import fr.diginamic.hello.services.VilleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villes;
    @Autowired
    private VilleRepository villeRepository;


    @GetMapping("/pagination")
    public Page<VilleDto> getVilles(@RequestParam int page, @RequestParam int taille) {
        PageRequest pageable = PageRequest.of(page, taille);
        return villes.extractVillePageable(pageable);
    }
    /**
     * Méthode extractVille qui renvoi la liste de villes connues dans la base
     * @return  la liste de toutes les villes
     */
    @GetMapping
    public List<VilleDto> extractVille(){

        return VilleMapper.toDtoList(villes.extractVille());
    }
    /**
     * Méthode extractVille
     * @param  id corresponf à l'id de la ville recherchée
     * @return la ville correspondant à l'id passé en paramètre
     */
    @GetMapping("/{id}")
    public VilleDto extractVille(@PathVariable("id")  Long id) {
        return VilleMapper.toDto(villes.extractVille(id));
    }

    /**
     * Méthode extractVille
     * @param  nom corresponf à l'id de la ville recherchée
     * @return la ville correspondant à l'id passé en paramètre
     */
    @GetMapping("/nom/{nom}")
    public Ville extractVille(@PathVariable("nom")  String nom) {
        return villes.extractVille(nom);
    }

    @PostMapping
    public ResponseEntity<String> insertVille(@RequestBody VilleDto ville) {

        try {
            villes.insertVille(ville);
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        }catch (Exception e) {
                return new ResponseEntity<String>("La ville existe déjà !",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> modifierVille( @RequestBody Ville ville) {

        try {
            villes.modifierVille(ville.getId(), ville);
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable Long id) {
        try{
            villes.supprimerVille(id);
            return new ResponseEntity<>("Succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Les n plus grandes villes d'un dep
     */
    @GetMapping("/rechercheVilleLesPlusPeuplees/{codeDep}/{n}")
    public List<VilleDto> rechercheVilleLesPlusPeuplees(@PathVariable("codeDep")String codeDep, @PathVariable("n") Integer n) {
        return VilleMapper.toDtoList(villes.rechercheVilleLesPlusPeuplees(codeDep,n));
    }

    /**
     * Recherche de toutes les villes dont la population est supérieure à min
     */
    @GetMapping("/rechercheVillePopulationSuperieureAMin/Min/{min}")
    public List<VilleDto> rechercheVillePopulationSuperieureAMin(@PathVariable("min")Integer min) {
        return VilleMapper.toDtoList(villes.findAllByNbHabitantsGreaterThan(min));
    }

    /**
     * Recherche de toutes les villes dont la population est supérieure à min et inférieure à max
     */
    @GetMapping("/rechercheVillePopulationSuperieureAMinInferieureAMax/Min/{min}/Max/{max}")
    public List<VilleDto> rechercheVillePopulationSuperieureAMinInferieureAMax(@PathVariable("min")Integer min,@PathVariable("max")Integer max ) {
        return VilleMapper.toDtoList(villes.findAllByNbHabitantsBetween(min,max));
    }

    /**
     * Recherche de toutes les villes d’un département dont la population est supérieure à min et inférieure à max
     */
    @GetMapping("/rechercheVilleDUnDepartementPopulationSuperieureAMinInferieureAMax/Departement/" +
            "{departement}/Min/{min}/Max/{max}")
    public List<VilleDto> rechercheVillePopulationSuperieureAMinInferieureAMax(@PathVariable("departement")Long dptId
            ,@PathVariable("min")Integer min,@PathVariable("max")Integer max ) {
        return VilleMapper.toDtoList(villes.findByDepartement_idAndNbHabitantsBetween(dptId,min,max));
    }

    /**
     * Recherche de toutes les villes d’un département dont la population est supérieure à min
     */
    @GetMapping("/rechercheVilleDUnDepartementPopulationSuperieureAMin/Departement/" +
            "{departement}/Min/{min}")
    public List<VilleDto> rechercheVilleDUnDepartementPopulationSuperieureAMin(@PathVariable("departement")Long dptId
            ,@PathVariable("min")Integer min) {
        return VilleMapper.toDtoList(villes.findByDepartement_idAndNbHabitantsGreaterThan(dptId,min));
    }

    /**
     * Méthode de génération d"un fichier pdf des villes
     */
    @GetMapping("/{nom}/fiche")
    public void ficheVille(@PathVariable String nom, HttpServletResponse response) throws IOException
    , DocumentException, VillesExceptions {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + nom + "_fiche.pdf");

        // Création du document PDF
        Document document = new Document();

        try {
            // Associer le flux de sortie au writer iText
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            // Ouvrir le document
            document.open();

            // Ajouter un titre
            document.addTitle("Fiche de la ville");
            document.add(new Paragraph("Fiche de la ville : " + nom, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD)));
            // Récupérer les informations de la ville depuis la base de données
            Ville ville =villes.extractVille(nom);
            // Ajouter les informations de la ville au PDF
            document.add(new Paragraph("Nom de la ville : " + ville.getNom(), new Font(Font.FontFamily.HELVETICA, 12)));
            document.add(new Paragraph("Population : " + ville.getNbHabitants(), new Font(Font.FontFamily.HELVETICA, 12)));
            document.add(new Paragraph("Département : " + ville.getDepartement(), new Font(Font.FontFamily.HELVETICA, 12)));
            // Ajouter une nouvelle page
            document.newPage();

            // Créer une police personnalisée
            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font customFont = new Font(baseFont, 32.0f, Font.BOLD, new BaseColor(0, 51, 80));

            // Ajouter du texte stylisé
            Phrase phrase = new Phrase("COUCOU", customFont);
            document.add(phrase);

        } catch (VillesExceptions.ErreurVilleAbsenteExceptions e) {
            throw new RuntimeException("Erreur lors de la génération du PDF : " + e.getMessage());
        } catch (com.itextpdf.text.DocumentException e) {
            throw new RuntimeException(e);
        } finally {
            // Fermer le document
            document.close();
        }
        response.flushBuffer();
    }





}
