import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Classe;
import entities.Etudiant;
import entities.Filiere;
import entities.Inscription;
import entities.Niveau;
import entities.Professeur;
import entities.ProfesseurDeClasse;
import repositories.ClasseRepository;
import repositories.EtudiantRepository;
import repositories.IClasse;
import repositories.IEtudiant;
import repositories.IInscription;
import repositories.IProfesseur;
import repositories.IProfesseurDeClasse;
import repositories.InscriptionRepository;
import repositories.ProfesseurDeClasseRepository;
import repositories.ProfesseurRepository;
import services.ClasseService;
import services.EtudiantService;
import services.IClasseService;
import services.IEtudiantService;
import services.IInscriptionService;
import services.IProfesseurService;
import services.InscriptionService;
import services.ProfesseurService;


public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        //Dependances Repository
        IProfesseur professeurRepository=new ProfesseurRepository();
        IProfesseurDeClasse professeurDeClasseRepository=new ProfesseurDeClasseRepository();
        IClasse classeRepository=new ClasseRepository();
        IEtudiant  etudiantRepository = new EtudiantRepository();
        IInscription inscriptionRepository= new InscriptionRepository();
        //Dependances Service
        IClasseService classeService=new ClasseService(classeRepository) ;
        IProfesseurService professeurService=new ProfesseurService(professeurRepository,professeurDeClasseRepository) ;
        IInscriptionService inscriptionService= new InscriptionService(inscriptionRepository);
        IEtudiantService etudiantService=new EtudiantService(etudiantRepository);
        //Dependances
       
        do {
            System.out.println("1-Creer des classes");
            System.out.println("2-Lister des classes"); 
            System.out.println("3-Ajouter les professeur et leur affecter des classes"); 
            System.out.println("4-Lister les professeurs");
            System.out.println("5-Filtrer les classes d'un professeur");
            System.out.println("6-Faire une inscription ou une reinscription");
            System.out.println("7-Lister les etudiants inscrits dans une année"); 
            System.out.println("8-Quitter"); 
             choix=sc.nextInt();
             sc.nextLine();
             
            switch (choix) {
                case 1:
                    int choixNiveau;
                    do {
                        System.out.println("Choisissez le niveau 1-L1; 2-L2; 3-L3) ");
                        choixNiveau= sc.nextInt();
                    } while (choixNiveau<1 || choixNiveau>3);
                
                    Niveau niveau = Niveau.values()[choixNiveau - 1];
                    // Choisir la filière
                    int choixFiliere;
                    do {
                        System.out.println("Choisissez la filière 1-IAGE; 2-GLRS; 3-MAE; 4-MOSIEF; 5-ETSE; 6-CPD; 7-CDSD) : ");
                        choixFiliere = sc.nextInt();
                    } while (choixFiliere<1 || choixFiliere>7);
                    
                    Filiere filiere = Filiere.values()[choixFiliere - 1];

                    Classe classe= new Classe();
                    classe.setNiveaux(niveau);
                    classe.setFiliere(filiere);

                    classeService.ajouterClasse(classe);
                    break;
                    
                case 2:
                    System.out.println("les classes sont les suivantes :");
                    List<Classe> classes=  classeService.listerClasse();
                    for (Classe cl: classes) {
                        System.out.println(cl.getNiveaux() +" "+ cl.getFiliere());
                    }
                    break;

                case 3:
                    Professeur professeur=new Professeur();
                    //1-Donner les Infos de commande (Numero) nci nomcomplet et grade
                    System.out.println("Entrer le nci");
                    professeur.setNci(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Entrer le Nom Complet");
                    professeur.setNomComplet(sc.nextLine());
                    System.out.println("Entrer le Grade");
                    professeur.setGrade(sc.nextLine());
                    //2-Ajouter 1 ou plusieurs produits a la commande 
                    //Apres chaque ajout recalculer le montant
                    classes = classeService.listerClasse();
                    int response=2;

                    //Créer une liste vide pour le professeur des classes
                    List<ProfesseurDeClasse> ListeClasseDuProf = new ArrayList<>();
                
                    do {
                        for (Classe cl: classes) {
                            System.out.println(cl.getId()+"-"+cl.getNiveaux() +" "+ cl.getFiliere());
                        }
                        System.out.println("Veuillez sélectionner la classe à laquelle vous voulez affecter un professeur");
                        int idClasse=sc.nextInt(); 
                        classe = classeService.findClasseById(idClasse);
                        if (classe!=null) {
                            ProfesseurDeClasse professeurDeClasse = new ProfesseurDeClasse(classe);
                            professeurDeClasse.setProfesseur(professeur);
                            //Ajouter la classe à la liste
                                //Tester si il n'a pas déjà reçu cette classe
                                int inside=0;
                            if(ListeClasseDuProf.size()>0){
                                for (ProfesseurDeClasse p: ListeClasseDuProf) {
                                    if(p.getClasse().getId() == classe.getId()){
                                        inside = 1;
                                        System.out.println("Classe déjà passée au professeur!");
                                    } 
                                }
                                if(inside == 0){
                                    ListeClasseDuProf.add(professeurDeClasse); 
                                }
                            }else{
                                ListeClasseDuProf.add(professeurDeClasse);    
                            }
                            
                        }else{
                            System.out.println("Cet Id n'existe pas");
                        } 
                         
                        System.out.println("Voulez continuez 1-Oui 2-Non"); 
                        response=sc.nextInt(); 
                       
                    } while (response==1);

                    professeur.setProfesseurDeClasses(ListeClasseDuProf);

                    if (professeur.getProfesseurDeClasses().size()<1) {
                        System.out.println("Le professeur doit avoir au moins une classe");
                    }else{
                        professeurService.ajouterUnProfesseur(professeur);
                    }
                    
                  
              break;
                case 4:
                    System.out.println("les professeurs sont les suivantes :");
                    List<Professeur> professeurs=  professeurService.listerProfesseurs();
                    for (Professeur pc: professeurs) {
                        System.out.println(pc.getGrade() +" :"+ pc.getNomComplet());
                    }
                break;
        
                case 5:
                System.out.println("les professeurs sont les suivantes :");
                    professeurs=  professeurService.listerProfesseurs();
                    for (Professeur pc: professeurs) {
                        System.out.println(pc.getId()+"-"+pc.getGrade() +" :"+ pc.getNomComplet());
                }
                System.out.println("Entrer l'ID");
                int idpr=sc.nextInt();
                professeur=professeurService.findProfesseurById(idpr);
                if (professeur != null) {
                    List<ProfesseurDeClasse> professeurDeClasses=professeur.getProfesseurDeClasses();
                    
                    for (ProfesseurDeClasse pc : professeurDeClasses) {
                        System.out.println("----------------------------------------------------");
                        System.out.println(pc.getClasse().getNiveaux()+" "+pc.getClasse().getFiliere());
                        }
                  }
                    break;
                case 6:
                    System.out.println("Entrer le matricule  de l'etudiant : ");
                    String matricule =sc.nextLine();
                    Inscription inscription=new Inscription();
                    inscription=inscriptionService.rechercherInscriptionParMatriculeEtudiant(matricule);
                    Etudiant etudiant= new Etudiant();
                    if(inscription==null){
                        System.out.println("L'etudiant n'a jamais été inscrit. Veuillez l'inscrire");
                        System.out.println("Entrer le matricule  de l'etudiant : ");
                        matricule =sc.nextLine();
                        System.out.println( "Veuiller entrer le nom complet de l'etudiant : ");
                        String nomComplet=sc.nextLine();
                        System.out.println( "Veuiller entrer le tuteur de l'etudiant : ");
                        String tuteur=sc.nextLine();
                        etudiant=new Etudiant();
                        etudiant.setMatricule(matricule);
                        etudiant.setNomComplet(nomComplet);
                        etudiant.setTuteur(tuteur);
                        etudiantService.ajouterEtudiant(etudiant);
                    }else{
                        System.out.println("Le matricule existe déja. Reinscrivez l'etudiant");
                    }
                    inscription=new Inscription();
                    System.out.println("Choisir l'année d'inscription");
                    String anneScolaire=sc.nextLine();
                    
                    System.out.println("les classes sont les suivantes :");
                    classes=  classeService.listerClasse();
                     for (Classe cl: classes) {
                          System.out.println(cl.getId()+" -"+cl.getNiveaux() +" "+ cl.getFiliere());
                     }
                     int idClasse=sc.nextInt();
                     classe= new  Classe();
                     classe=classeService.findClasseById(idClasse);
                     inscription.setClasse(classe);
                     inscription.setAnneScolaire(anneScolaire);
                     inscription.setEtudiant(etudiant);
                     inscriptionService.faireInscription(inscription);
                    break;
                    
                case 7:
                    System.out.println("Saisir l'année d'inscription");
                    anneScolaire=sc.nextLine();
                    List<Inscription> inscriptions= inscriptionService.listerInscriptionParAnneeScolaire(anneScolaire);
                    for (Inscription insc : inscriptions) {
                        System.out.println("Matricule : "+insc.getEtudiant().getMatricule()+"\n Nom Complet : "+insc.getEtudiant().getNomComplet()+"\n Tuteur : "+insc.getEtudiant().getTuteur());
                        System.out.println("=================================================");
                    }
                    System.out.println("Voulez vous filter cette liste par classe ?  oui/non");
                    String reponse= sc.nextLine();
                    
                    if(reponse.equalsIgnoreCase("oui")){
                        System.out.println("les classes sont les suivantes :");
                        classes=  classeService.listerClasse();
                        for (Classe cl: classes) {
                            System.out.println(cl.getId()+" -"+cl.getNiveaux() +" "+ cl.getFiliere());
                        }
                        idClasse=sc.nextInt();

                        inscriptions= inscriptionService.listerInscriptionParAnneeScolaire(anneScolaire,idClasse);
                        for (Inscription insc : inscriptions) {
                            System.out.println("Matricule : "+insc.getEtudiant().getMatricule()+"\n Nom Complet : "+insc.getEtudiant().getNomComplet()+"\n Tuteur : "+insc.getEtudiant().getTuteur());
                            System.out.println("=================================================");
                        }
                    }else if(reponse.equalsIgnoreCase("non")){
                        break;
                    }
                    default:
                        break;
                }

        } while (choix!=8);
    }
}
