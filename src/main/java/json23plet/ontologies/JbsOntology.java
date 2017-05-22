
package json23plet.ontologies;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class JbsOntology
    extends BaseOntology
{
    private static Paths paths;
    public static Path ontPath = (Paths.get("ontologies", "ttl", "JbsOntology" + ".ttl"));
    public static ModelFactory smodel;
    public static OntModel model = ((OntModel) ((OntModel) ModelFactory.createOntologyModel()).read(ontPath.toString()));
    public static String JBO_PREFIX = "jbo";
    public static String JBR_PREFIX = "jbr";
    public static String JBO_URI = "http://jbs.technion.ac.il/ontology/";
    public static String JBR_URI = "http://jbs.technion.ac.il/resource/";
    public static Resource JBO_C_JBSPACKAGE = (model.getOntClass(JBO_URI + "JbsPackage"));
    public static Resource JBO_C_SEFER = (model.getOntClass(JBO_URI + "Sefer"));
    public static Resource JBO_C_PARASHA = (model.getOntClass(JBO_URI + "Parasha"));
    public static Resource JBO_C_PEREK = (model.getOntClass(JBO_URI + "Perek"));
    public static Resource JBO_C_TANACH = (model.getOntClass(JBO_URI + "Tanach"));
    public static Resource JBO_C_MENTION = (model.getOntClass(JBO_URI + "Mention"));
    public static Resource JBO_C_CHASIDUT = (model.getOntClass(JBO_URI + "Chasidut"));
    public static Resource JBO_C_LIKUTEYMOHARAN = (model.getOntClass(JBO_URI + "LikuteyMoharan"));
    public static Resource JBO_C_MISHNA = (model.getOntClass(JBO_URI + "Mishna"));
    public static Resource JBO_C_SEDERZERAIM = (model.getOntClass(JBO_URI + "SederZeraim"));
    public static Resource JBO_C_SEDERMOED = (model.getOntClass(JBO_URI + "SederMoed"));
    public static Resource JBO_C_SEDERNASHIM = (model.getOntClass(JBO_URI + "SederNashim"));
    public static Resource JBO_C_SEDERNEZIKIN = (model.getOntClass(JBO_URI + "SederNezikin"));
    public static Resource JBO_C_SEDERKADASHIM = (model.getOntClass(JBO_URI + "SederKadashim"));
    public static Resource JBO_C_SEDERTAHAROT = (model.getOntClass(JBO_URI + "SederTaharot"));
    public static Resource JBO_C_PERUSHMISHNA = (model.getOntClass(JBO_URI + "PerushMishna"));
    public static Resource JBO_C_PERUSHBARTANURA = (model.getOntClass(JBO_URI + "PerushBartanura"));
    public static Resource JBO_C_PERUSHYOMTOV = (model.getOntClass(JBO_URI + "PerushYomTov"));
    public static Resource JBO_C_TALMUDBAVLI = (model.getOntClass(JBO_URI + "TalmudBavli"));
    public static Resource JBO_C_AMUDBAVLI = (model.getOntClass(JBO_URI + "AmudBavli"));
    public static Resource JBO_C_BAVLIPACKAGE = (model.getOntClass(JBO_URI + "BavliPackage"));
    public static Resource JBO_C_PERUSHBAVLI = (model.getOntClass(JBO_URI + "PerushBavli"));
    public static Resource JBO_C_PERUSHBAVLIRASHI = (model.getOntClass(JBO_URI + "PerushBavliRashi"));
    public static Resource JBO_C_PERUSHBAVLITOSAFOT = (model.getOntClass(JBO_URI + "PerushBavliTosafot"));
    public static Resource JBO_C_PERUSHBAVLIRASHBAM = (model.getOntClass(JBO_URI + "PerushBavliRashbam"));
    public static Resource JBO_C_PERUSHBAVLIRAN = (model.getOntClass(JBO_URI + "PerushBavliRan"));
    public static Resource JBO_C_AMUDBERACHOT = (model.getOntClass(JBO_URI + "AmudBerachot"));
    public static Resource JBO_C_AMUDSHABAT = (model.getOntClass(JBO_URI + "AmudShabat"));
    public static Resource JBO_C_AMUDERUVIN = (model.getOntClass(JBO_URI + "AmudEruvin"));
    public static Resource JBO_C_AMUDPESACHIM = (model.getOntClass(JBO_URI + "AmudPesachim"));
    public static Resource JBO_C_AMUDROSHHASHANA = (model.getOntClass(JBO_URI + "AmudRoshHashana"));
    public static Resource JBO_C_AMUDYOMA = (model.getOntClass(JBO_URI + "AmudYoma"));
    public static Resource JBO_C_AMUDSUKA = (model.getOntClass(JBO_URI + "AmudSuka"));
    public static Resource JBO_C_AMUDBETZA = (model.getOntClass(JBO_URI + "AmudBetza"));
    public static Resource JBO_C_AMUDTAANIT = (model.getOntClass(JBO_URI + "AmudTaanit"));
    public static Resource JBO_C_AMUDMEGILA = (model.getOntClass(JBO_URI + "AmudMegila"));
    public static Resource JBO_C_AMUDMOEDKATAN = (model.getOntClass(JBO_URI + "AmudMoedKatan"));
    public static Resource JBO_C_AMUDCHAGIGA = (model.getOntClass(JBO_URI + "AmudChagiga"));
    public static Resource JBO_C_AMUDYEVAMOT = (model.getOntClass(JBO_URI + "AmudYevamot"));
    public static Resource JBO_C_AMUDKETUVOT = (model.getOntClass(JBO_URI + "AmudKetuvot"));
    public static Resource JBO_C_AMUDNEDARIM = (model.getOntClass(JBO_URI + "AmudNedarim"));
    public static Resource JBO_C_AMUDNAZIR = (model.getOntClass(JBO_URI + "AmudNazir"));
    public static Resource JBO_C_AMUDSOTA = (model.getOntClass(JBO_URI + "AmudSota"));
    public static Resource JBO_C_AMUDGITIN = (model.getOntClass(JBO_URI + "AmudGitin"));
    public static Resource JBO_C_AMUDKIDUSHIN = (model.getOntClass(JBO_URI + "AmudKidushin"));
    public static Resource JBO_C_AMUDBAVAKAMA = (model.getOntClass(JBO_URI + "AmudBavaKama"));
    public static Resource JBO_C_AMUDBAVAMETZIA = (model.getOntClass(JBO_URI + "AmudBavaMetzia"));
    public static Resource JBO_C_AMUDBAVABATRA = (model.getOntClass(JBO_URI + "AmudBavaBatra"));
    public static Resource JBO_C_AMUDSANHEDRIN = (model.getOntClass(JBO_URI + "AmudSanhedrin"));
    public static Resource JBO_C_AMUDMAKOT = (model.getOntClass(JBO_URI + "AmudMakot"));
    public static Resource JBO_C_AMUDSHEVUOT = (model.getOntClass(JBO_URI + "AmudShevuot"));
    public static Resource JBO_C_AMUDAVODAZARA = (model.getOntClass(JBO_URI + "AmudAvodaZara"));
    public static Resource JBO_C_AMUDHORAYOT = (model.getOntClass(JBO_URI + "AmudHorayot"));
    public static Resource JBO_C_AMUDZEVACHIM = (model.getOntClass(JBO_URI + "AmudZevachim"));
    public static Resource JBO_C_AMUDMENACHOT = (model.getOntClass(JBO_URI + "AmudMenachot"));
    public static Resource JBO_C_AMUDCHULIN = (model.getOntClass(JBO_URI + "AmudChulin"));
    public static Resource JBO_C_AMUDBECHOROT = (model.getOntClass(JBO_URI + "AmudBechorot"));
    public static Resource JBO_C_AMUDARACHIN = (model.getOntClass(JBO_URI + "AmudArachin"));
    public static Resource JBO_C_AMUDTEMURA = (model.getOntClass(JBO_URI + "AmudTemura"));
    public static Resource JBO_C_AMUDKERITUT = (model.getOntClass(JBO_URI + "AmudKeritut"));
    public static Resource JBO_C_AMUDMEILA = (model.getOntClass(JBO_URI + "AmudMeila"));
    public static Resource JBO_C_AMUDTAMID = (model.getOntClass(JBO_URI + "AmudTamid"));
    public static Resource JBO_C_AMUDNIDA = (model.getOntClass(JBO_URI + "AmudNida"));
    public static Resource JBO_C_MISHNETORAH = (model.getOntClass(JBO_URI + "MishneTorah"));
    public static Resource JBO_C_MISHNETORAHPACKAGE = (model.getOntClass(JBO_URI + "MishneTorahPackage"));
    public static Resource JBO_C_MITZVASEFERHAMITZVOT = (model.getOntClass(JBO_URI + "MitzvaSeferHamitzvot"));
    public static Resource JBO_C_MITZVATASESEFERHAMITZVOT = (model.getOntClass(JBO_URI + "MitzvatAseSeferHamitzvot"));
    public static Resource JBO_C_MITZVATLOTAASESEFERHAMITZVOT = (model.getOntClass(JBO_URI + "MitzvatLoTaaseSeferHamitzvot"));
    public static Resource JBO_C_HALACHAMISHNETORAH = (model.getOntClass(JBO_URI + "HalachaMishneTorah"));
    public static Resource JBO_C_PERUSHMISHNETORAH = (model.getOntClass(JBO_URI + "PerushMishneTorah"));
    public static Resource JBO_C_PERUSHPERUSH = (model.getOntClass(JBO_URI + "PerushPerush"));
    public static Resource JBO_C_PERUSHKESEFMISHNE = (model.getOntClass(JBO_URI + "PerushKesefMishne"));
    public static Resource JBO_C_PERUSHLECHEMMISHNE = (model.getOntClass(JBO_URI + "PerushLechemMishne"));
    public static Resource JBO_C_PERUSHRAABAD = (model.getOntClass(JBO_URI + "PerushRaabad"));
    public static Resource JBO_C_PERUSHMAGIDMISHNE = (model.getOntClass(JBO_URI + "PerushMagidMishne"));
    public static Resource JBO_C_HALACHASEFERHAMADA = (model.getOntClass(JBO_URI + "HalachaSeferHamada"));
    public static Resource JBO_C_HALACHASEFERAHAVA = (model.getOntClass(JBO_URI + "HalachaSeferAhava"));
    public static Resource JBO_C_HALACHASEFERZEMANIM = (model.getOntClass(JBO_URI + "HalachaSeferZemanim"));
    public static Resource JBO_C_HALACHASEFERNASHIM = (model.getOntClass(JBO_URI + "HalachaSeferNashim"));
    public static Resource JBO_C_HALACHASEFERKEDUSHA = (model.getOntClass(JBO_URI + "HalachaSeferKedusha"));
    public static Resource JBO_C_HALACHASEFERHAFLAA = (model.getOntClass(JBO_URI + "HalachaSeferHaflaa"));
    public static Resource JBO_C_HALACHASEFERZERAIM = (model.getOntClass(JBO_URI + "HalachaSeferZeraim"));
    public static Resource JBO_C_HALACHASEFERAVODA = (model.getOntClass(JBO_URI + "HalachaSeferAvoda"));
    public static Resource JBO_C_HALACHASEFERKORBANOT = (model.getOntClass(JBO_URI + "HalachaSeferKorbanot"));
    public static Resource JBO_C_HALACHASEFERTAHARA = (model.getOntClass(JBO_URI + "HalachaSeferTahara"));
    public static Resource JBO_C_HALACHASEFERNEZIKIN = (model.getOntClass(JBO_URI + "HalachaSeferNezikin"));
    public static Resource JBO_C_HALACHASEFERKINYAN = (model.getOntClass(JBO_URI + "HalachaSeferKinyan"));
    public static Resource JBO_C_HALACHASEFERMISHPATIM = (model.getOntClass(JBO_URI + "HalachaSeferMishpatim"));
    public static Resource JBO_C_HALACHASEFERSHOFTIM = (model.getOntClass(JBO_URI + "HalachaSeferShoftim"));
    public static Resource JBO_C_SHULCHANARUCH = (model.getOntClass(JBO_URI + "ShulchanAruch"));
    public static Resource JBO_C_HALACHASHULCHANARUCH = (model.getOntClass(JBO_URI + "HalachaShulchanAruch"));
    public static Resource JBO_C_HALACHAORACHCHAIM = (model.getOntClass(JBO_URI + "HalachaOrachChaim"));
    public static Resource JBO_C_HALACHAYOREDEA = (model.getOntClass(JBO_URI + "HalachaYoreDea"));
    public static Resource JBO_C_HALACHAEVENHAEZER = (model.getOntClass(JBO_URI + "HalachaEvenHaezer"));
    public static Resource JBO_C_HALACHACHOSHENMISHPAT = (model.getOntClass(JBO_URI + "HalachaChoshenMishpat"));
    public static Resource JBO_C_SIFREYRAVKUK = (model.getOntClass(JBO_URI + "SifreyRavKuk"));
    public static Resource JBO_C_SIFREYRAMCHAL = (model.getOntClass(JBO_URI + "SifreyRamchal"));
    public static Resource JBO_C_PASUK = (model.getOntClass(JBO_URI + "Pasuk"));
    public static Resource JBO_C_PERUSHTANACH = (model.getOntClass(JBO_URI + "PerushTanach"));
    public static Resource JBO_C_TANACHPACKAGE = (model.getOntClass(JBO_URI + "TanachPackage"));
    public static Resource JBO_C_PASUKTORAH = (model.getOntClass(JBO_URI + "PasukTorah"));
    public static Resource JBO_C_PASUKNEVIYIM = (model.getOntClass(JBO_URI + "PasukNeviyim"));
    public static Resource JBO_C_PASUKKETUVIM = (model.getOntClass(JBO_URI + "PasukKetuvim"));
    public static Resource JBO_C_PASUKBERESHIT = (model.getOntClass(JBO_URI + "PasukBereshit"));
    public static Resource JBO_C_PASUKSHEMOT = (model.getOntClass(JBO_URI + "PasukShemot"));
    public static Resource JBO_C_PASUKVAYIKRA = (model.getOntClass(JBO_URI + "PasukVayikra"));
    public static Resource JBO_C_PASUKBAMIDBAR = (model.getOntClass(JBO_URI + "PasukBamidbar"));
    public static Resource JBO_C_PASUKDEVARIM = (model.getOntClass(JBO_URI + "PasukDevarim"));
    public static Resource JBO_C_PASUKYEHOSHUA = (model.getOntClass(JBO_URI + "PasukYehoshua"));
    public static Resource JBO_C_PASUKSHOFTIM = (model.getOntClass(JBO_URI + "PasukShoftim"));
    public static Resource JBO_C_PASUKSHEMUELA = (model.getOntClass(JBO_URI + "PasukShemuelA"));
    public static Resource JBO_C_PASUKSHEMUELB = (model.getOntClass(JBO_URI + "PasukShemuelB"));
    public static Resource JBO_C_PASUKMELACHIMA = (model.getOntClass(JBO_URI + "PasukMelachimA"));
    public static Resource JBO_C_PASUKMELACHIMB = (model.getOntClass(JBO_URI + "PasukMelachimB"));
    public static Resource JBO_C_PASUKYESHAAYA = (model.getOntClass(JBO_URI + "PasukYeshaaya"));
    public static Resource JBO_C_PASUKYIRMIYA = (model.getOntClass(JBO_URI + "PasukYirmiya"));
    public static Resource JBO_C_PASUKYECHEZKEL = (model.getOntClass(JBO_URI + "PasukYechezkel"));
    public static Resource JBO_C_PASUKHOSHEA = (model.getOntClass(JBO_URI + "PasukHoshea"));
    public static Resource JBO_C_PASUKYOEL = (model.getOntClass(JBO_URI + "PasukYoel"));
    public static Resource JBO_C_PASUKAMOS = (model.getOntClass(JBO_URI + "PasukAmos"));
    public static Resource JBO_C_PASUKOVADYA = (model.getOntClass(JBO_URI + "PasukOvadya"));
    public static Resource JBO_C_PASUKYONA = (model.getOntClass(JBO_URI + "PasukYona"));
    public static Resource JBO_C_PASUKMICHA = (model.getOntClass(JBO_URI + "PasukMicha"));
    public static Resource JBO_C_PASUKNACHUM = (model.getOntClass(JBO_URI + "PasukNachum"));
    public static Resource JBO_C_PASUKCHAVAKUK = (model.getOntClass(JBO_URI + "PasukChavakuk"));
    public static Resource JBO_C_PASUKTZEFANYA = (model.getOntClass(JBO_URI + "PasukTzefanya"));
    public static Resource JBO_C_PASUKCHAGAY = (model.getOntClass(JBO_URI + "PasukChagay"));
    public static Resource JBO_C_PASUKZECHARYA = (model.getOntClass(JBO_URI + "PasukZecharya"));
    public static Resource JBO_C_PASUKMALACHI = (model.getOntClass(JBO_URI + "PasukMalachi"));
    public static Resource JBO_C_PASUKTEHILIM = (model.getOntClass(JBO_URI + "PasukTehilim"));
    public static Resource JBO_C_PASUKMISHLEY = (model.getOntClass(JBO_URI + "PasukMishley"));
    public static Resource JBO_C_PASUKIYOV = (model.getOntClass(JBO_URI + "PasukIyov"));
    public static Resource JBO_C_PASUKSHIRHASHIRIM = (model.getOntClass(JBO_URI + "PasukShirHashirim"));
    public static Resource JBO_C_PASUKRUT = (model.getOntClass(JBO_URI + "PasukRut"));
    public static Resource JBO_C_PASUKEYCHA = (model.getOntClass(JBO_URI + "PasukEycha"));
    public static Resource JBO_C_PASUKKOHELET = (model.getOntClass(JBO_URI + "PasukKohelet"));
    public static Resource JBO_C_PASUKESTER = (model.getOntClass(JBO_URI + "PasukEster"));
    public static Resource JBO_C_PASUKDANIEL = (model.getOntClass(JBO_URI + "PasukDaniel"));
    public static Resource JBO_C_PASUKEZRA = (model.getOntClass(JBO_URI + "PasukEzra"));
    public static Resource JBO_C_PASUKNECHEMYA = (model.getOntClass(JBO_URI + "PasukNechemya"));
    public static Resource JBO_C_PASUKDIVREYHAYAMIMA = (model.getOntClass(JBO_URI + "PasukDivreyHayamimA"));
    public static Resource JBO_C_PASUKDIVREYHAYAMIMB = (model.getOntClass(JBO_URI + "PasukDivreyHayamimB"));
    public static Resource JBO_C_PERUSHRASHI = (model.getOntClass(JBO_URI + "PerushRashi"));
    public static Resource JBO_C_PERUSHSIFTEYCHACHAMIM = (model.getOntClass(JBO_URI + "PerushSifteyChachamim"));
    public static Resource JBO_C_PERUSHRAMBAN = (model.getOntClass(JBO_URI + "PerushRamban"));
    public static Resource JBO_C_TARGUMYONATAN = (model.getOntClass(JBO_URI + "TargumYonatan"));
    public static Resource JBO_C_PERUSHORHACHAYIM = (model.getOntClass(JBO_URI + "PerushOrHachayim"));
    public static Resource JBO_C_PERUSHIBNEZRA = (model.getOntClass(JBO_URI + "PerushIbnEzra"));
    public static Resource JBO_C_PERUSHBAALHATURIM = (model.getOntClass(JBO_URI + "PerushBaalHaturim"));
    public static Resource JBO_C_TARGUMONKELOS = (model.getOntClass(JBO_URI + "TargumOnkelos"));
    public static Resource JBO_C_PERUSHSEFORNO = (model.getOntClass(JBO_URI + "PerushSeforno"));
    public static Resource JBO_C_PERUSHKELIYEKAR = (model.getOntClass(JBO_URI + "PerushKeliYekar"));
    public static Resource JBO_C_PERUSHDAATZEKENIM = (model.getOntClass(JBO_URI + "PerushDaatZekenim"));
    public static Resource JBO_C_MIDRASHRABA = (model.getOntClass(JBO_URI + "MidrashRaba"));
    public static Resource JBO_C_MIDRASHTANCHUMA = (model.getOntClass(JBO_URI + "MidrashTanchuma"));
    public static Resource JBO_C_PERUSHMETZUDATDAVID = (model.getOntClass(JBO_URI + "PerushMetzudatDavid"));
    public static Resource JBO_C_PERUSHMETZUDATTZIYON = (model.getOntClass(JBO_URI + "PerushMetzudatTziyon"));
    public static Resource JBO_C_PERUSHRALBAG = (model.getOntClass(JBO_URI + "PerushRalbag"));
    public static Resource JBO_C_PERUSHMALBIM = (model.getOntClass(JBO_URI + "PerushMalbim"));
    public static Resource JBO_C_PERUSHMALBIMBEURHAINYAN = (model.getOntClass(JBO_URI + "PerushMalbimBeurHainyan"));
    public static Resource JBO_C_PERUSHMALBIMBEURHAMILOT = (model.getOntClass(JBO_URI + "PerushMalbimBeurHamilot"));
    public static Resource JBO_C_SHEMONAKEVATZIM = (model.getOntClass(JBO_URI + "ShemonaKevatzim"));
    public static Resource JBO_C_OROT = (model.getOntClass(JBO_URI + "Orot"));
    public static Resource JBO_C_MESILATYESHARIM = (model.getOntClass(JBO_URI + "MesilatYesharim"));
    public static Property JBO_P_MENTIONS = (model.getOntProperty(JBO_URI + "mentions"));
    public static Property JBO_P_INTERPRETS = (model.getOntProperty(JBO_URI + "interprets"));
    public static Property JBO_P_CONTENT = (model.getOntProperty(JBO_URI + "content"));
    public static Property JBO_P_CONTAINER = (model.getOntProperty(JBO_URI + "container"));
    public static Property JBO_P_POSITION = (model.getOntProperty(JBO_URI + "position"));
    public static Property JBO_P_TEXT = (model.getOntProperty(JBO_URI + "text"));
    public static Property JBO_P_TEXTNIKUD = (model.getOntProperty(JBO_URI + "textNikud"));
    public static Property JBO_P_POSITIONINPEREK = (model.getOntProperty(JBO_URI + "positionInPerek"));
    public static Property JBO_P_MASECHET = (model.getOntProperty(JBO_URI + "masechet"));
    public static Property JBO_P_PEREK = (model.getOntProperty(JBO_URI + "perek"));
    public static Property JBO_P_MEFARESH = (model.getOntProperty(JBO_URI + "mefaresh"));
    public static Property JBO_P_POSITIONINPARASHA = (model.getOntProperty(JBO_URI + "positionInParasha"));
    public static Property JBO_P_SOURCE = (model.getOntProperty(JBO_URI + "source"));
    public static Property JBO_P_TARGET = (model.getOntProperty(JBO_URI + "target"));
    public static Property JBO_P_SPAN = (model.getOntProperty(JBO_URI + "span"));
    public static Property JBO_P_NUMOFMENTIONS = (model.getOntProperty(JBO_URI + "numOfMentions"));
}
