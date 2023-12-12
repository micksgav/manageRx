

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListGen {

	public static void main(String[] args) throws IOException, InterruptedException {
		generateInitialFile();
		listCleanup();
		scraper();
	}
	
	private static void generateInitialFile() throws IOException, InterruptedException {
		String[] names = new String[] { "Abacavir Sulfate", "Abarelix", "Abatacept", "Abciximab", "Abelcet", "Abilify",
				"Abraxane", "Acamprosate Calcium", "Acarbose", "Accolate", "Accretropin", "AccuNeb", "Accupril",
				"Accutane", "Acebutolol", "Aceon", "Acetadote", "Acetaminophen", "Acetic Acid", "Aci-Jel", "Aciphex",
				"Acitretin", "Aclovate", "Acrivastine and Pseudoephedrine", "Actemra", "Acthrel", "Acticin",
				"Actimmune", "Actiq", "Actisite", "Activase", "Activella", "Actonel", "Actos", "Acular", "Acular LS",
				"Acuvail", "Acyclovir", "Adacel", "Adagen", "Adalat CC", "Adalimumab", "Adapalene", "Adcirca",
				"Adderall", "Adderall XR", "Adefovir Dipivoxil", "Adenoscan", "Adenosine", "Adipex-P", "Adoxa",
				"Adrenalin", "AdreView", "Adriamycin PFS", "Advair Diskus", "Advair HFA", "Advicor", "Aerospan HFA",
				"Afinitor", "Afluria", "Agalsidase Beta", "Aggrastat", "Aggrenox", "Agrylin", "Ak-Fluor", "Akineton",
				"Alamast", "Albendazole", "Albenza", "Albumin, Human", "Albuminar", "Albuterol Sulfate",
				"Albuterol Sulfate Inhalation Aerosol", "Alcaine", "Alclometasone Dipropionate", "Aldactazide",
				"Aldactone", "Aldara", "Aldesleukin", "Aldomet", "Aldoril", "Aldurazyme", "Alefacept", "Alemtuzumab",
				"Alendronate Sodium", "Alesse", "Alfenta", "Alfentanil", "Alfuzosin HCl", "Alglucosidase Alfa",
				"Alimta", "Alinia", "Alitretinoin", "Alkeran", "Allegra", "Allegra-D", "Allegra D 24 Hour", "Allernaze",
				"Alli", "Allopurinol", "Almotriptan Malate", "Alocril", "Alomide", "Aloprim", "Alora",
				"Alosetron Hydrochloride", "Aloxi", "Alphanate", "Alprazolam", "Alprostadil", "Alrex", "Alsuma",
				"Altabax", "Altace", "Alteplase", "Altocor", "Altoprev", "Altretamine", "Alupent", "Alvesco",
				"Amantadine Hydrochloride", "Amaryl", "Ambien", "Ambien CR", "Ambisome", "Amerge", "Americaine",
				"Amevive", "Amicar", "Amifostine", "Amikacin", "Amikin", "Amiloride",
				"Amiloride and Hydrochlorothiazide", "Amiloride Hydrochloride", "Amino Acids", "Aminocaproic Acid",
				"Aminoglutethimide", "Aminohippurate Sodium", "Aminolevulinic Acid", "Aminosalicylic Acid",
				"Aminosyn II 8.5%", "Aminosyn II in Dextrose", "Amitiza", "Amitriptyline", "Amlexanox",
				"Amlodipine Besylate", "Amoxapine", "Amoxicillin", "Amoxicillin Clavulanate", "Amoxil", "Amphadase",
				"Amphetamine", "Amphotericin B", "Ampicillin", "Ampyra", "Amrix", "Amyl Nitrite", "Amytal Sodium",
				"Anabolic steroids", "Anadrol-50", "Anafranil", "Anagrelide", "Anakinra", "Anastrozole", "Ancobon",
				"Androderm", "AndroGel", "Anectine", "Angeliq", "Angiomax", "Anidulafungin", "Anisindione", "Ansaid",
				"Antabuse", "Antara", "Anthralin", "Antihemophilic Factor", "Antithrombin", "Antivenin", "Antivert",
				"Antizol", "Anturane", "Anusol Hc", "Aphthasol", "Apidra", "Aplenzin", "Apokyn", "Apomorphine",
				"Apraclonidine", "Apresazide", "Apresoline", "Apri", "Apriso", "Aprotinin", "Aptivus", "Aquasol A",
				"Aralen", "Aramine", "Aranesp", "Arava", "Arcalyst", "Aredia", "Argatroban", "Aricept", "Arimidex",
				"Aripiprazole", "Aristocort", "Arixtra", "Armodafinil", "Aromasin", "Arranon", "Artane", "Arthrotec",
				"Arzerra", "Asacol", "Asacol HD", "Asclera", "Ascorbic Acid", "Asmanex Twisthaler", "Asparaginase",
				"Aspirin", "Astelin", "Astemizole", "Astepro", "Atacand", "Atacand HCT", "Atazanavir Sulfate",
				"Atenolol and Chlorthalidone", "Atgam", "Ativan", "Atomoxetine HCl", "Atorvastatin Calcium",
				"Atovaquone", "Atracurium Besylate", "Atralin", "Atridox", "Atripla", "Atromid-S", "Atropen",
				"Atropine", "Atrovent HFA", "Atryn", "Augmentin", "Augmentin XR", "Auralgan", "Avage", "Avalide",
				"Avandamet", "Avandaryl", "Avandia", "Avapro", "Avastin", "Avelox", "Avinza", "Avodart", "Avonex",
				"Axert", "Axid", "Aygestin", "Azacitidine", "Azasan", "Azasite", "Azathioprine", "Azelaic Acid",
				"Azelastine Hydrochloride", "Azelex", "Azilect", "Azithromycin", "Azmacort", "Azopt", "Azor",
				"Azulfidine EN-Tabs", "Bacitracin", "Baclofen", "Bactrim", "Bactroban Nasal", "Balsalazide", "Banzel",
				"Baraclude", "Basiliximab", "Baycol", "Bayer", "Becaplermin", "Beconase", "Benadryl", "Benazepril",
				"Benefix", "Benicar", "Benicar HCT", "Bentyl", "BenzaClin", "Benzagel", "Benzamycin", "Benzocaine",
				"Benzonatate", "Benzoyl Peroxide Gel", "Benzphetamine", "Benztropine Mesylate", "Bepreve", "Bepridil",
				"Beractant", "Besivance", "Betagan", "Betamethasone", "Betamethasone Dipropionate", "Betapace",
				"Betapace AF", "Betaseron", "Betaxolol Hydrochloride", "Betaxon", "Bethanechol", "Bethanechol Chloride",
				"Betimol", "Betoptic S", "Bevacizumab", "Bexarotene", "Bextra", "Bexxar", "Bicalutamide",
				"Bicillin C-R 900/300", "BiCNU", "BiDil", "Biltricide", "Bioclate", "BioThrax", "Biperiden",
				"Bismuth Subcitrate Potassium", "Bismuth Subsalicylate", "Bisoprolol Fumarate", "Bivalirudin",
				"Blenoxane", "Bleph 10", "Blocadren", "Boniva", "Bontril", "Boostrix", "Bortezomib", "Bosentan",
				"Botox", "Botox Cosmetic", "Botulinum Toxin Type A", "Botulinum Toxin Type B", "Bravelle", "Bretylium",
				"Brevibloc", "Brevicon", "Brevital Sodium", "Brimonidine Tartrate", "Bromfenac Ophthalmic Solution",
				"Bromocriptine Mesylate", "Brovana", "Budesonide", "Bumetanide", "Bumex", "Buphenyl", "Buprenex",
				"Buprenorphine", "Bupropion Hcl", "Buspar", "Buspirone", "Busulfan", "Busulfex", "Butenafine",
				"Butisol", "Butoconazole", "Butorphanol Tartrate", "Butrans", "Byetta", "Ca-DTPA", "Cabergoline",
				"Caduet", "Cafcit", "Cafergot", "Caffeine Citrate", "Calan", "Calciferol", "Calcitonin-Salmon",
				"Calcitriol", "Calcium Chloride", "Calcium Disodium Versenate", "Calcium Gluconate", "Calfactant",
				"Cambia", "Campath", "Campral", "Canasa", "Cancidas", "Candesartan Cilexetil", "Cantil",
				"Capastat Sulfate", "Capecitabine", "Capoten", "Capozide", "Captopril",
				"Captopril and Hydrochlorothiazide", "Carac", "Carbaglu", "Carbamazepine", "Carbatrol",
				"Carbenicillin Indanyl Sodium", "Carbidopa", "Carbocaine", "Carboplatin", "Carboprost Tromethamine",
				"Cardene SR", "Cardiolite", "Cardizem LA", "Cardura", "Cardura XL", "Carisoprodol",
				"Carisoprodol and Aspirin", "Carmustine", "Carnitor", "Carteolol", "Carteolol Hydrochloride",
				"Cartia XT", "Carvedilol", "Casodex", "Caspofungin Acetate", "Cataflam", "Catapres", "Catapres-TTS",
				"Caverject", "Caverject Impulse", "Cayston", "Ceclor", "Cedax", "CeeNU", "Cefaclor", "Cefadroxil",
				"Cefadroxil Hemihydrate", "Cefamandole", "Cefdinir", "Cefditoren Pivoxil", "Cefixime", "Cefizox",
				"Cefobid", "Cefotan", "Cefotaxime", "Cefotetan", "Cefoxitin", "Cefprozil", "Ceftazidime", "Ceftibuten",
				"Ceftin", "Ceftizoxime", "Ceftriaxone", "Cefuroxime", "Cefuroxime Axetil", "Cefzil", "Celebrex",
				"Celecoxib", "Celestone Soluspan", "Celexa", "CellCept", "Cellulose", "Celontin", "Cenestin",
				"Cephalexin", "Ceprotin", "Ceptaz", "Cerebyx", "Ceredase", "Ceretec", "Cerezyme", "Cerivastatin",
				"Certolizumab Pegol", "Cerubidine", "Cerumenex", "Cervarix", "Cervidil", "Cesamet", "Cetirizine",
				"Cetraxal", "Cetrorelix", "Cetrotide", "Cetuximab", "Cevimeline HCL", "Chantix", "Chemet", "Chibroxin",
				"ChiRhoStim", "Chlor-Trimeton", "Chloral Hydrate", "Chlorambucil", "Chloramphenicol",
				"Chloramphenicol Sodium Succinate", "Chlordiazepoxide", "Chlorhexidine", "Chloroprocaine", "Chloroptic",
				"Chloroquine", "Chlorothiazide", "Chlorpheniramine Maleate", "Chlorpromazine", "Chlorpropamide",
				"Chlorthalidone", "Chlorzoxazone", "Cholera Vaccine", "Cholestyramine",
				"Choline Magnesium Trisalicylate", "Chorionic Gonadotropin", "Cialis", "Ciclopirox Gel", "Cidofovir",
				"Cilostazol", "Cimetidine", "Cimzia", "Cinacalcet", "Cinobac", "Cinoxacin", "Cinryze", "Cipro",
				"Cipro XR", "Ciprodex", "Ciprofloxacin", "Ciprofloxacin and Dexamethasone", "Ciprofloxacin Hcl",
				"Cisapride", "Cisatracurium Besylate", "Cisplatin", "Citalopram Hydrobromide", "Cladribine", "Claforan",
				"Clarinex", "Clarinex-D 12 Hour", "Clarinex-D 24 Hour", "Clarithromycin", "Claritin", "Cleocin",
				"Cleocin T", "Clevidipine Butyrate", "Cleviprex", "Climara", "Climara Pro", "Clindamycin",
				"Clindamycin Phosphate", "Clindets", "Clinoril", "Clobetasol Propionate", "Clobetasol Propionate Gel",
				"Clocortolone", "Cloderm", "Clofarabine", "Clofazimine", "Clofibrate", "Clolar", "Clomid", "Clomiphene",
				"Clomipramine Hcl", "Clonazepam", "Clonidine", "Clopidogrel Bisulfate", "Clorazepate Dipotassium",
				"Clorpres", "Clotrimazole", "Clozapine", "Clozaril", "Coartem", "Cocaine", "Codeine",
				"Codeine Phosphate", "Codeine Sulfate", "Cogentin", "Cognex", "Colazal", "Colchicine", "Colcrys",
				"Colesevelam Hcl", "Colestid", "Colestipol", "Collagenase", "Coly-Mycin M", "Coly-Mycin S Otic",
				"Combigan", "CombiPatch", "Combivent", "Combivir", "Combunox", "Compazine", "Compro", "Comtan",
				"Comvax", "Concerta", "Conjugated Estrogens", "Copaxone", "Copegus", "Cordarone", "Coreg", "Coreg CR",
				"Corgard", "Corlopam", "Cortaid", "Cortef", "Cortenema", "Cortisone Acetate", "Cortone", "Cortrosyn",
				"Corvert", "Corzide", "Cosmegen", "Cosopt", "Cosyntropin", "Coumadin", "Covera-HS", "Cozaar", "Creon",
				"Crestor", "Crixivan", "Crofab", "Crolom", "Cromolyn Sodium", "Cubicin", "Cuprimine", "Curosurf",
				"Cuvposa", "Cyanocobalamin", "Cyanokit", "Cyclessa", "Cyclobenzaprine Hcl", "Cyclophosphamide",
				"Cycloset", "Cyclosporine", "Cyklokapron", "Cylert", "Cymbalta", "Cyproheptadine",
				"Cyproheptadine Hydrochloride", "Cystadane", "Cystagon", "Cysteamine Bitartrate", "Cysview", "Cytadren",
				"Cytarabine", "Cytogam", "Cytomel", "Cytotec", "Cytovene", "Cytoxan", "Dacarbazine", "Daclizumab",
				"Dacogen", "Dactinomycin", "Dalmane", "Dalteparin", "Danazol", "Dapsone", "Daptacel", "Daraprim",
				"Darbepoetin Alfa", "Darunavir", "Darvocet-N", "Darvon", "Darvon Compound", "Dasatinib", "Daunorubicin",
				"Daypro", "Daypro Alta", "Daytrana", "DDAVP", "DDAVP Nasal Spray", "DDAVP Rhinal Tube", "Decadron",
				"Declomycin", "Deferasirox", "Deferoxamine", "Definity", "Dehydrated Alcohol", "Delatestryl",
				"Delavirdine Mesylate", "Delestrogen", "Deltasone", "Demadex", "Demeclocycline HCl", "Demerol",
				"Demser", "Demulen", "Denavir", "Denileukin Diftitox", "Depacon", "Depakene", "Depakote", "Depakote ER",
				"Depo-Estradiol", "Depo-SubQ Provera", "Depo-Testosterone", "DepoCyt", "DepoDur", "Derma-Smoothe/FS",
				"Desferal", "Desflurane", "Desipramine Hydrochloride", "Desloratadine",
				"Desloratadine and Pseudoephedrine Sulfate", "Desmopressin Acetate", "Desogen",
				"Desogestrel and Ethinyl Estradiol", "Desonate", "DesOwen", "Desoximetasone", "Desoxyn", "Desyrel",
				"Detrol", "Detrol LA", "Dexamethasone", "Dexedrine", "Dexfenfluramine", "Dexilant", "Dexlansoprazole",
				"Dexmedetomidine hydrochloride", "Dexmethylphenidate Hydrochloride", "Dexrazoxane", "Dextroamphetamine",
				"Dht", "DiaBeta", "Diabinese", "Diamox Sequels", "Diastat", "Diazepam", "Dibenzyline",
				"Diclofenac Sodium", "Dicloxacillin", "Dicyclomine", "Didanosine", "Didrex", "Didronel", "Dienestrol",
				"Diethylpropion", "Difenoxin", "Differin", "Diflucan", "Diflunisal", "Digibind", "Digitek",
				"Digoxin Immune Fab", "Dihydroergotamine", "Dihydrotachysterol", "Dilacor XR", "Dilantin",
				"Dilantin Infatabs", "Dilaudid", "Dilaudid-HP", "Diltiazem", "Diltiazem Hcl", "Dimetane",
				"Dinoprostone", "Diovan", "Diovan HCT", "Dipentum", "Diphenhydramine", "Diphtheria-Tetanus Vaccine",
				"Dipivefrin", "Diprivan", "Diprolene AF", "Dipyridamole", "Disalcid", "Disopyramide Phosphate",
				"Disulfiram", "Ditropan", "Ditropan XL", "Diucardin", "Diuril", "Divalproex Sodium", "DMSO",
				"Dobutamine", "Dofetilide", "Dolasetron", "Dolobid", "Dolophine", "Donepezil Hydrochloride", "Dopamine",
				"Dopamine Hydrochloride", "Dopar", "Dopram", "Doral", "Doribax", "Dornase alfa", "Doryx", "Dorzolamide",
				"Dostinex", "Dovonex", "Doxacurium Chloride", "Doxapram", "Doxazosin Mesylate", "Doxepin",
				"Doxercalciferol", "Doxil", "Doxorubicin hydrochloride", "Doxycycline", "Doxycycline Hyclate",
				"Drisdol", "Dronabinol", "Dronedarone", "Droperidol", "Drospirenone and Estradiol", "Drotrecogin alfa",
				"Droxia", "Dtic-Dome", "DTP", "Duetact", "Dulera", "Duloxetine Hcl", "Duoneb", "Duraclon", "Duragesic",
				"Duramorph", "Duranest", "Durezol", "Duricef", "Dutasteride", "Dyazide", "Dynacirc", "Dynacirc CR",
				"Dynapen", "Dyphylline", "Dyrenium", "Dysport", "Econazole Nitrate", "Eculizumab", "Edecrin",
				"Edetate Calcium", "Edex", "Edluar", "Edrophonium", "Efalizumab", "Efavirenz", "Effexor", "Effexor XR",
				"Effient", "Eflornithine", "Efudex", "Elaprase", "Elavil", "Eldepryl", "Elestat",
				"Eletriptan hydrobromide", "Elidel", "Eligard", "Elimite", "Elitek", "Ellence", "Elmiron", "Elocon",
				"Eloxatin", "Elspar", "Eltrombopag", "Emadine", "Embeda", "Emcyt", "Emedastine", "Emend", "Emgel",
				"Emla", "Empirin", "Emsam", "Emtricitabine", "Emtriva", "Enablex", "Enalapril", "Enbrel", "Endocet",
				"Endometrin", "Endrate", "Enflurane", "Enfuvirtide", "Engerix-B", "Enjuvia", "Enlon", "Enoxacin",
				"Enoxaparin", "Entacapone", "Entecavir", "Entereg", "Entex", "Entocort EC", "Eovist", "Ephedrine",
				"Epiduo", "Epinastine HCl", "Epinephrine", "Epipen", "Epirubicin hydrochloride", "Epivir", "Eplerenone",
				"Epoetin Alfa", "Epogen", "Epoprostenol sodium", "Eprosartan Mesylate", "Eptifibatide", "Epzicom",
				"Equagesic", "Equetro", "Eraxis", "Erbitux", "Ergocalciferol", "Ergomar",
				"Ergotamine Tartrate and Caffeine", "Erlotinib", "Ertaczo", "Ertapenem", "Ery-Tab", "Eryc", "Erygel",
				"EryPed", "Erythrocin Stearate", "Erythromycin", "Erythromycin Ethylsuccinate",
				"Erythromycin Lactobionate", "Escitalopram Oxalate", "Esclim", "Eskalith", "Esmolol",
				"Esomeprazole Magnesium", "Esomeprazole Sodium", "Essential Amino Acids", "Estazolam",
				"Esterified Estrogens", "Estrace", "Estraderm", "Estradiol", "Estradiol Acetate", "Estradiol valerate",
				"Estramustine", "Estratest", "Estring", "EstroGel", "Estrogens conjugated", "Estropipate",
				"Estrostep 21", "Eszopiclone", "Etanercept", "Ethacrynic Acid", "Ethambutol", "Ethamolin",
				"Ethanolamine Oleate", "EtheDent", "Ethinyl Estradiol", "Ethiodized Oil", "Ethiodol", "Ethionamide",
				"Ethosuximide", "Ethotoin", "Ethrane", "Ethyl Chloride", "Ethyol", "Etidocaine HCl",
				"Etidronate Disodium", "Etodolac", "Etonogestrel", "Etopophos", "Etoposide", "Etoposide Phosphate",
				"Etrafon", "Eulexin", "Eurax", "Evamist", "Everolimus", "Evista", "Evoclin", "Evoxac", "Exalgo",
				"Exelderm", "Exelon", "Exemestane", "Exenatide", "Exforge", "Exforge HCT", "Exjade", "Extavia",
				"Extended Phenytoin Sodium", "Extina", "Extraneal", "Exubera", "Ezetimibe", "Ezetimibe and Simvastatin",
				"Fabrazyme", "Factive", "Factor IX Complex", "Factrel", "Famciclovir", "Famotidine",
				"Famotidine Injection", "Famvir", "Fanapt", "Fansidar", "Fareston", "Faslodex", "Fastin", "Fazaclo",
				"Febuxostat", "Feiba-VH", "Felbamate", "Felbatol", "Feldene", "Felodipine", "Femara", "Femcon Fe",
				"Femhrt", "Femring", "Fenofibrate", "Fenofibric Acid", "Fenoglide", "Fenoldopam Mesylate",
				"Fenoprofen Calcium", "Fentanyl Buccal", "Fentanyl Citrate", "Fentora", "Feraheme", "Feridex I.V.",
				"Ferrlecit", "Fertinex", "Ferumoxides", "Ferumoxytol", "Fesoterodine Fumarate Extended",
				"Fexofenadine Hcl", "Filgrastim", "Finacea", "Finasteride", "Fioricet", "Fiorinal with Codeine",
				"Flagyl", "Flavoxate HCl", "Flecainide", "Flector", "Flexeril", "Flo-Pred", "Flolan", "Flomax",
				"Flonase", "Florinef", "Flovent", "Flovent Diskus", "Flovent HFA", "Floxin", "Floxin Otic",
				"Floxuridine", "Fluconazole", "Flucytosine", "Fludara", "Fludarabine", "Fludarabine Phosphate",
				"Fludeoxyglucose F 18", "Fludrocortisone", "Flumadine", "Flumazenil", "FluMist", "Flunisolide",
				"Fluocinolone Acetonide", "Fluocinonide", "Fluorescein", "Fluorescite", "Fluoride", "Fluorometholone",
				"Fluoroplex", "Fluorouracil", "Fluothane", "Fluoxetine Hcl", "Fluoxetine Hydrochloride",
				"Fluoxymesterone", "Fluphenazine", "Flurandrenolide", "Flurazepam", "Flurbiprofen", "Fluress",
				"Flutamide", "Fluticasone Furoate", "Fluticasone Propionate", "Fluvastatin Sodium", "Fluvirin",
				"Fluvoxamine Maleate", "FML", "FML Forte", "Focalin", "Focalin XR", "Folic Acid", "Follistim AQ",
				"Follitropin Alfa", "Follitropin Beta", "Folotyn", "Foltx", "Folvite", "Fomepizole", "Fomivirsen",
				"Fondaparinux Sodium", "Foradil Aerolizer", "Foradil Certihaler", "Forane",
				"Formoterol Fumarate Inhalation Powder", "Fortamet", "Fortaz", "Forteo", "Fortical", "Fosamax",
				"Fosamax Plus D", "Fosamprenavir Calcium", "Fosaprepitant Dimeglumine", "Foscarnet Sodium", "Foscavir",
				"Fosfomycin", "Fosinopril Sodium", "Fosphenytoin Sodium", "Fospropofol Disodium", "Fosrenol", "Fragmin",
				"Frova", "Frovatriptan Succinate", "Fulvestrant", "Fungizone", "Furadantin", "Furazolidone",
				"Furosemide", "Furoxone", "Fuzeon", "Gabapentin", "Gabitril", "Gadobenate Dimeglumine", "Gadodiamide",
				"Gadofosveset Trisodium", "Gadopentetate Dimeglumine", "Gadoteridol", "Gadoversetamide",
				"Gadoxetate Disodium", "Galantamine", "Galsulfase", "Gammagard", "Gamunex", "Ganciclovir", "Ganirelix",
				"Ganirelix Acetate Injection", "Gantanol", "Gantrisin", "Gardasil", "Gatifloxacin", "Gelnique",
				"Gemcitabine Hcl", "Gemfibrozil", "Gemifloxacin Mesylate", "Gemtuzumab Ozogamicin", "Gemzar", "Gengraf",
				"Genoptic", "Genotropin", "Gentak", "Gentamicin", "Geocillin", "Geodon", "Glatiramer Acetate",
				"Gleevec", "Gliadel", "Glimepiride", "Glipizide", "GlucaGen", "Glucagon", "Glucophage XR", "Glucotrol",
				"Glucotrol XL", "Glucovance", "Glumetza", "Glyburide", "Glycopyrrolate", "Glynase", "Glyset",
				"Gold Sodium Thiomalate", "Golimumab", "Gonadorelin", "Gonal-F", "Gonal-f RFF", "Goserelin Acetate",
				"Goserelin Acetate Implant", "Granisetron", "Granisetron Hydrochloride", "Grepafloxacin", "Grifulvin V",
				"Gris-PEG", "Griseofulvin", "Guaifenesin", "Guanethidine Monosulfate", "Guanfacine",
				"Guanfacine Hydrochloride", "Gynazole", "Gyne-Lotrimin", "Haemophilus b Conjugate Vaccine",
				"Halcinonide", "Halcion", "Haldol", "Haldol Decanoate", "HalfLytely and Bisacodyl",
				"Halobetasol Propionate", "Halog", "Haloperidol", "Haloperidol Decanoate", "Halotestin", "Halothane",
				"Havrix", "Healon", "Hectorol", "Helidac", "Hemabate", "Hemin", "HEP-LOCK U/P", "HepaGam B", "Heparin",
				"Heparin Lock Flush", "Heparin Sodium", "HepatAmine", "Hepatitis A Vaccine, Inactivated",
				"Hepatitis B Immune Globulin", "Hepatitis B Vaccine", "Hepatitis B Vaccine Recombinant", "Hepflush-10",
				"Hepsera", "Herceptin", "Hexachlorophene", "Hexalen", "HibTITER", "Hiprex", "Hismanal", "Hivid", "HMS",
				"Humalog", "Human Secretin", "Humatrope", "Humira", "Humorsol", "Humulin N", "Humulin R", "Hyalgan",
				"Hycamtin", "Hycodan", "Hydralazine", "Hydrea", "Hydrochlorothiazide and Triamterene",
				"Hydrocodone and Ibuprofen", "Hydrocodone Bitartrate and Acetaminophen",
				"Hydrocodone Bitartrate and Acetaminophen Tablets", "Hydrocortisone", "Hydrocortisone and Acetic Acid",
				"Hydrocortisone Butyrate", "Hydrocortisone Sodium Succinate", "Hydroflumethiazide",
				"Hydromorphone Hydrochloride", "Hydroquinone", "Hydroxychloroquine", "Hydroxyethyl Starch",
				"Hydroxyurea", "Hydroxyzine", "Hydroxyzine Hydrochloride", "Hylan G-F 20", "Hylenex", "Hyoscyamine",
				"Hyoscyamine Sulfate", "Hytrin", "Hyzaar", "Ibandronate Sodium", "Ibritumomab Tiuxetan", "Ibuprofen",
				"Ibuprofen Lysine", "Ibutilide Fumarate", "Ic-Green", "Icodextrin", "Idamycin", "Idamycin PFS",
				"Idarubicin", "Ifex", "Ifosfamide", "Ilaris", "Iloperidone", "Iloprost", "Ilotycin",
				"Imatinib Mesylate", "Imiglucerase", "Imipenem / cilastatin", "Imipramine", "Imipramine Pamoate",
				"Imiquimod", "Imitrex", "Immune Globulin", "Immune Globulin Intravenous", "Imodium", "Imovax",
				"Implanon", "Imuran", "Inapsine", "Increlex", "Indapamide", "Inderal", "Inderal LA", "Inderide",
				"Indigo Carmine", "Indigotindisulfonate", "Indinavir Sulfate", "Indocin", "Indomethacin", "Infanrix",
				"Infasurf", "Infed", "Infergen", "Infliximab", "Influenza Virus Vaccine", "Innohep", "InnoPran XL",
				"Inomax", "Insoluble Prussian blue", "Inspra", "Insulin", "Insulin Aspart", "Insulin Detemir",
				"Insulin Glulisine", "Insulin Human", "Insulin Lispro", "Intal Nebulizer", "Integrilin", "Intelence",
				"Interferon alfa-2a", "Interferon alfa-2b", "Interferon Alfacon-1", "Interferon beta-1a",
				"Interferon beta-1b", "Interferon Gamma 1-b", "Intralipid 10%", "Intralipid 20%", "Intron A", "Intuniv",
				"Invanz", "Invega", "Invega Sustenna", "Inversine", "Invirase", "Iobenguane I 123", "Ionamin", "Ionsys",
				"Ioxilan", "Iplex", "Ipol", "Irbesartan", "Irbesartan-Hydrochlorothiazide", "Iressa",
				"Irinotecan Hydrochloride", "Iron Dextran", "Isentress", "Ismelin", "Ismo", "Isocarboxazid",
				"Isoflurane", "Isoniazid", "Isoproterenol", "Isoptin SR", "Isopto Carpine", "Isopto Hyoscine",
				"Isordil", "Isosorbide Dinitrate", "Isosorbide Mononitrate", "Isosulfan Blue", "Isotretinoin",
				"Isradipine", "Istalol", "Istodax", "Isuprel", "Ivermectin", "Ixabepilone", "Ixempra", "Ixiaro",
				"Jadelle", "Jalyn", "Jantoven", "Janumet", "Januvia", "Japanese Encephalitis Vaccine", "Je-Vax",
				"Jevtana", "K-LOR", "K-Tab", "Kadian", "Kalbitor", "Kaletra", "Kanamycin", "Kantrex", "Kapidex",
				"Kariva", "Kayexalate", "Keflex", "Kemstro", "Kenalog", "Kepivance", "Keppra", "Keppra XR", "Kerlone",
				"Ketamine HCl", "Ketamine Hydrochloride", "Ketek", "Ketoconazole", "Ketoprofen",
				"Ketorolac Tromethamine", "Ketotifen Fumarate", "Kineret", "Kinevac", "Kinlytic", "Kionex", "Klaron",
				"Klonopin", "Klor-Con", "Kogenate FS", "Kuvan", "Kytril", "Labetalol", "Lac-Hydrin", "lacosamide",
				"Lacrisert", "Lactated Ringer's Solution", "Lactic Acid", "Lactulose", "Lamictal", "Lamisil",
				"Lamivudine", "Lamivudine / Zidovudine", "Lamotrigine", "Lamprene", "Lanoxin", "Lansoprazole", "Lantus",
				"Lapatinib", "Lariam", "Laronidase", "Lasix", "Lastacaft", "Latanoprost", "Latisse", "Leflunomide",
				"Lenalidomide", "Lepirudin", "Lescol", "Letairis", "Letrozole", "Leucovorin Calcium", "Leukeran",
				"Leukine", "Leuprolide Acetate", "Leustatin", "Levalbuterol", "Levaquin", "Levbid", "Levemir",
				"Levetiracetam", "Levitra", "Levo-Dromoran", "Levo-T", "Levobetaxolol Hydrochloride", "Levobunolol",
				"Levocabastine", "Levocarnitine", "Levocetirizine Dihydrochloride", "Levodopa", "Levofloxacin",
				"Levoleucovorin", "Levomethadyl Acetate", "Levonorgestrel", "Levonorgestrel and Ethinyl Estradiol",
				"Levonorgestrel Implants", "Levonorgestrel, Ethinyl Estradiol", "Levophed", "Levora", "Levorphanol",
				"Levothroid", "Levothyroxine Sodium", "Levoxyl", "Levsin", "Levsin SL", "Levulan Kerastick", "Lexapro",
				"Lexiscan", "Lexiva", "Lexxel", "Lialda", "Librax", "Librium", "Lidex", "Lidocaine",
				"Lidocaine and Prilocaine", "Lidocaine and tetracaine", "Lidoderm", "Limbitrol", "Lincocin",
				"Lincomycin Hcl", "Lindane", "Linezolid", "Lioresal Intrathecal", "Liothyronine Sodium", "Liotrix",
				"Lipitor", "Lipofen", "Liposyn II", "Liposyn III", "Liraglutide", "Lisdexamfetamine Dimesylate",
				"Lisinopril", "Lisinopril and Hydrochlorothiazide", "Lithium Carbonate", "Livalo", "Livostin", "Locoid",
				"Locoid Lipocream", "Lodine", "Lodosyn", "Lodoxamide Tromethamine", "Loestrin 24 Fe",
				"Lomefloxacin Hcl", "Lomotil", "Lomustine", "Loperamide Hcl", "Lopid", "Lopinavir/Ritonavir",
				"Lopressor", "Lopressor HCT", "Loprox Cream", "Loprox Gel", "Lorabid", "Loracarbef", "Loratadine",
				"Lorazepam", "Lortab", "Losartan Potassium", "Lotemax", "Lotensin", "Lotensin Hct",
				"Loteprednol Etabonate", "Lotrel", "Lotrisone", "Lotronex", "Lovastatin", "Lovaza", "Lovenox",
				"Low-Ogestrel", "Loxapine", "Loxapine Succinate", "Loxitane", "Lozol", "Lubiprostone", "Lucentis",
				"Lufyllin", "Lumigan", "Lumizyme", "Lunesta", "Lupron", "Lusedra", "Luvox CR", "Luxiq", "Lybrel",
				"Lymphazurin", "Lyrica", "Lysodren", "Lysteda", "M-M-R", "Macrobid", "Macrodantin", "Macugen",
				"Mafenide Acetate", "Magnesium Sulfate", "Magnevist", "Malarone", "Malathion", "Mandol", "Mangafodipir",
				"Mannitol", "Maraviroc", "Marcaine", "Marinol", "Marplan", "Matulane", "Mavik", "Maxair", "Maxalt",
				"Maxaquin", "Maxipime", "Maxitrol", "Measles Vaccine", "Mebaral", "Mebendazole", "Mecamylamine",
				"Mecasermin", "Mecasermin Rinfabate", "Mechlorethamine HCl", "Meclizine", "Meclofenamate", "Medrol",
				"Medroxyprogesterone", "Medroxyprogesterone Acetate", "Medrysone", "Mefenamic Acid", "Mefloquine",
				"Mefoxin", "Megace", "Megace ES", "Megestrol Acetate", "Mellaril", "Meloxicam", "Melphalan",
				"Memantine HCL", "Menactra", "Menest", "Meningococcal Vaccine", "Menomune", "Menopur", "Menostar",
				"Menotropins", "Mentax", "Mepenzolate Bromide", "Meperidine", "Mephobarbital", "Mephyton",
				"Mepivacaine", "Meprobamate", "Meprobamate / Aspirin", "Mepron", "Mequinol and Tretinoin",
				"Mercaptopurine", "Meridia", "Meropenem", "Merrem I.V.", "Mesalamine", "Mesna", "Mesnex", "Mestinon",
				"Metadate CD", "Metadate ER", "Metaglip", "Metaproterenol Sulfate", "Metaraminol", "Metastron",
				"Metaxalone", "Metformin Hcl", "Methacholine Chloride", "Methadone", "Methadone Hydrochloride",
				"Methadose Oral Concentrate", "Methamphetamine Hydrochloride", "Methazolamide", "Methenamine Hippurate",
				"Methergine", "Methimazole", "Methocarbamol", "Methohexital Sodium", "Methotrexate", "Methoxsalen",
				"Methsuximide", "Methyclothiazide", "Methyldopa", "Methyldopate Hcl", "Methylene Blue",
				"Methylergonovine Maleate", "Methylin", "Methylnaltrexone Bromide", "Methylphenidate Hcl",
				"Methylprednisolone", "Methylprednisolone Sodium Succinate", "Methyltestosterone",
				"Methysergide maleate", "Metipranolol", "Metoclopramide", "Metolazone", "Metopirone",
				"Metoprolol Succinate", "Metoprolol Tartrate", "Metozolv ODT", "MetroCream", "Metrodin", "Metrogel",
				"MetroLotion", "Metronidazole", "Metvixia", "Metyrapone", "Metyrosine", "Mevacor", "Mexiletine HCl",
				"Mexitil", "Miacalcin", "Micafungin Sodium", "Micardis", "Micardis HCT", "Miconazole", "Micro-K",
				"Micronase", "Micronized Glyburide", "Microzide", "Midamor", "Midazolam", "Midodrine Hydrochloride",
				"Midrin", "Mifeprex", "Mifepristone", "Miglitol", "Miglustat", "Migranal", "Milnacipran HCl",
				"Milrinone", "Minipress", "Minocin", "Minocycline", "Minocycline Hydrochloride", "Minoxidil",
				"Mintezol", "Miochol-E", "Miostat", "Miradon", "MiraLAX", "Mirapex", "Mirapex ER", "Mircera",
				"Mircette", "Mirena", "Mirtazapine", "Misoprostol", "Mithracin", "Mitomycin", "Mitotane",
				"Mitoxantrone", "Moban", "Mobic", "Modafinil", "Moduretic", "Moexipril", "Moexipril HCl",
				"Molindone Hydrochloride", "Mometasone Furoate", "Monistat", "Monistat-Derm", "Monoclate-P", "Monodox",
				"Mononine", "Monopril", "Montelukast Sodium", "Monurol", "Morphine Sulfate", "Morrhuate Sodium",
				"Motofen", "Motrin", "Moxatag", "Moxifloxacin", "Moxifloxacin HCL", "Mozobil", "MS Contin", "Multaq",
				"Multi Vitamin", "Multihance", "Mumps Vaccine", "Mupirocin", "Mustargen", "Mutamycin", "Myambutol",
				"Mycamine", "Mycelex", "Mycobutin", "Mycophenolate Mofetil", "Mycophenolic Acid", "Mycostatin",
				"Myfortic", "Mykrox", "Myleran", "Mylotarg", "Myobloc", "Myochrysine", "Myozyme", "Mysoline",
				"Mytelase", "Nabi-HB", "Nabumetone", "Nadolol", "Nadolol and Bendroflumethiazide", "Nafcillin Sodium",
				"Naftifine", "Naftifine Hcl", "Naglazyme", "Nalbuphine hydrochloride", "Nalfon", "Nalidixic Acid",
				"Nalmefene Hydrochloride", "Naloxone", "Naltrexone", "Namenda", "Naprelan", "Naproxen",
				"Naproxen Sodium", "Naratriptan", "Narcan", "Nardil", "Naropin", "Nasacort AQ", "Nasalcrom", "Nasalide",
				"Nascobal", "Nasonex", "Natacyn", "Natalizumab", "Natamycin", "Natazia", "Nateglinide", "Natrecor",
				"Navane", "Navelbine", "Nebcin", "Nebivolol Tablets", "Nebupent", "Necon", "Nedocromil",
				"Nedocromil Inhalation Aerosol", "Nefazodone", "NegGram", "Nelarabine", "Nelfinavir Mesylate",
				"Nembutal", "Neo-Synephrine", "Neodecadron", "Neomycin Sulfate", "NeoProfen", "Neoral", "Neostigmine",
				"Neostigmine Methylsulfate", "Nephramine", "Nesacaine", "Nesiritide", "Neulasta", "Neumega", "Neupogen",
				"Neupro", "Neurontin", "Neutrexin", "Nevanac", "Nevirapine", "Nexavar", "Nexium", "Niacin", "Niacor",
				"Niaspan", "Nicardipine Hydrochloride", "Nicotrol", "Nicotrol NS", "Nifedipine", "Niferex-150",
				"Nilandron", "Nilotinib Capsules", "Nilutamide", "Nimbex", "Nimodipine", "Nimotop", "Niravam",
				"Nisoldipine", "Nitazoxanide", "Nitisinone", "Nitric Oxide", "Nitro-Dur", "Nitrofurantoin",
				"Nitroglycerin", "Nitrolingual Pumpspray", "NitroMist", "Nitropress", "Nitroprusside Sodium",
				"Nitrostat", "Nizatidine", "Nizoral", "Noctec", "Nolvadex", "Nor-QD", "Norco", "Norditropin",
				"Norepinephrine Bitartrate", "Norethindrone", "Norethindrone and Ethinyl Estradiol", "Norflex",
				"Norfloxacin", "Norgesic", "Norgestimate and Ethinyl Estradiol", "Noritate", "Normal Saline", "Noroxin",
				"Norpace", "Norplant", "Norpramin", "Nortriptyline HCl", "Nortriptyline Hydrochloride", "Norvasc",
				"Norvir", "Novantrone", "Novolin R", "NovoLog", "NovoLog Mix 70/30", "Novoseven", "Novothyrox",
				"Noxafil", "Nplate", "Nubain", "Nucynta", "Numorphan", "Nuromax", "Nutropin", "Nutropin AQ",
				"Nutropin Depot", "NuvaRing", "Nuvigil", "Nydrazid", "Nystatin", "Nystop", "Ocufen", "Ocuflox",
				"Ofatumumab", "Ofloxacin", "Oforta", "Ogen", "Olanzapine", "Oleptro", "Olmesartan Medoxomil",
				"Olopatadine", "Olux", "Olux-E", "Omalizumab", "Omega-3-Acid Ethyl Esters", "Omeprazole", "Omnaris",
				"Omnicef", "Omnipred", "Omniscan", "Omnitrope", "Oncaspar", "Ondansetron Hydrochloride", "Onglyza",
				"Onsolis", "Ontak", "Opana", "Opana ER", "Oprelvekin", "Opticrom", "OptiMARK", "Optipranolol",
				"Optiray Injection", "Optison", "Optivar", "Oracea", "Orap", "Orapred ODT", "Oraqix", "Oravig",
				"Orencia", "Orfadin", "Organidin NR", "Orimune", "Orlaam", "Orlistat", "Orphenadrine",
				"Orphenadrine Citrate", "Orphengesic", "Ortho Evra", "Ortho Tri Cyclen", "Ortho-Novum", "Orudis",
				"Oseltamivir Phosphate", "Osmitrol", "OsmoPrep", "Ovcon", "Ovide", "Ovidrel", "Oxacillin",
				"Oxaliplatin", "Oxandrin", "Oxandrolone", "Oxaprozin", "Oxazepam", "Oxcarbazepine", "Oxiconazole",
				"Oxilan", "Oxistat", "Oxsoralen-Ultra", "Oxybutynin Chloride", "Oxycodone and Acetaminophen",
				"Oxycodone HCl", "Oxycodone Hydrochloride", "Oxycontin", "Oxymetholone", "Oxymorphone",
				"Oxymorphone Hydrochloride", "Oxymorphone Hydrochloride Extended Release", "Oxytetracycline",
				"Oxytocin", "Oxytrol", "Paclitaxel", "Palifermin", "Paliperidone", "Palivizumab", "Palonosetron",
				"Palonosetron hydrochloride", "Pamelor", "Pamidronate Disodium", "Pancrecarb", "Pancrelipase", "Pandel",
				"Panhematin", "Panretin", "Pantoprazole", "Pantoprazole Sodium", "Papain", "Papaverine",
				"Parafon Forte", "Paraplatin", "Paregoric", "Paricalcitol", "Parlodel", "Parnate",
				"Paromomycin Sulfate", "Paroxetine Hydrochloride", "Paroxetine Mesylate", "Paser", "Pataday",
				"Patanase", "Patanol", "Paxil", "Pazopanib", "PCE", "Pediapred", "Pediarix", "Pediazole", "Pediotic",
				"PEG", "PEG 3350", "Peg-Intron", "Pegademase Bovine", "Peganone", "Pegaptanib Sodium", "Pegaspargase",
				"Pegasys", "Pegfilgrastim", "Peginterferon alfa-2a", "Peginterferon alfa-2b", "PegIntron",
				"Pegvisomant", "Pemetrexed", "Pemirolast Potassium", "Pemoline", "Penciclovir", "Penetrex",
				"Penicillamine", "Penicillin G Benzathine", "Penicillin G Potassium", "Penicillin V Potassium",
				"Penicillin VK", "Penlac", "PENNSAID", "Pentamidine Isethionate", "Pentasa",
				"Pentazocine and Acetaminophen", "Pentazocine and Aspirin", "Pentetate Zinc Trisodium", "Pentobarbital",
				"Pentosan Polysulfate Sodium", "Pentothal", "Pentoxifylline", "Pepcid", "Percocet", "Percodan",
				"Perflutren", "Perforomist", "Pergolide Mesylate", "Perindopril Erbumine", "Periochip", "Periostat",
				"Permax", "Permethrin", "Perphenazine", "Persantine", "Persantine IV", "Pexeva", "Pfizerpen",
				"Phenazopyridine", "Phendimetrazine Tartrate", "Phenelzine", "Phenergan", "Phenobarbital",
				"Phenoxybenzamine", "Phentermine", "Phentermine Hydrochloride", "Phentolamine Mesylate",
				"Phenylephrine HCl", "Phenylephrine Hydrochloride", "Phenytoin", "Phisohex", "Phoslo", "Phosphate",
				"Phosphocol", "Phospholine Iodide", "Photofrin", "Physostigmine Salicylate", "Phytonadione",
				"Pilocarpine", "Pilocarpine Hydrochloride", "Pilopine HS", "Pimecrolimus", "Pimozide", "Pindolol",
				"Pioglitazone Hcl", "Piperacillin / Tazobactam", "Pipracil", "Pirbuterol", "Piroxicam", "Pitavastatin",
				"Pitocin", "Pitressin", "Plan B", "Plaquenil", "PlasmaLyte A", "Plavix", "Plenaxis", "Plendil",
				"Plerixafor", "Pletal", "Pliaglis", "Plicamycin", "Pneumococcal 7-valent vaccine",
				"Pneumococcal Vaccine Polyvalent", "Pneumovax", "Podocon-25", "Podofilox", "Podophyllin", "Polidocanol",
				"Polifeprosan 20", "Poliovirus Vaccine", "Polyethylene Glycol 3350", "Polymyxin B",
				"Polymyxin B Sulfate", "Polysaccharides", "Polythiazide", "Pondimin", "Ponstel", "Poractant Alfa",
				"Porfimer Sodium", "Posaconazole", "Potassium Acetate", "Potassium Chloride", "Potassium Citrate",
				"Pralatrexate", "Pralidoxime Chloride", "Pramipexole", "Pramlintide Acetate", "Prandimet", "Prandin",
				"Prasugrel", "Pravachol", "Pravastatin Sodium", "Praziquantel", "Prazosin HCl", "Precedex", "Precose",
				"Pred Forte", "Pred-G", "Prednicarbate", "Prednisolone", "Prednisolone Acetate",
				"Prednisolone Sodium Phosphate", "Prednisone", "Prefest", "Pregabalin", "Pregnyl", "Prelone",
				"Premarin", "Prempro, Premphase", "Prepidil", "Prevacid", "Prevnar", "Prevpac", "Prezista", "Prialt",
				"Priftin", "Prilosec", "Primacor", "Primaquine", "Primatene Mist", "Primaxin I.V.", "Primidone",
				"Principen", "Prinivil", "Prinzide", "Pristiq", "Privigen", "Proair HFA", "Proamatine",
				"Probenecid and Colchicine", "Procainamide", "Procalamine", "Procan Sr", "Procarbazine", "Procardia",
				"Prochlorperazine", "Prochlorperazine Maleate", "Procrit", "Proctofoam HC", "Progesterone", "Proglycem",
				"Prograf", "ProHance", "Prolastin", "Proleukin", "Prolia", "Prolixin", "Promacta", "Promethazine",
				"Promethazine HCl", "Promethazine Hydrochloride", "Prometrium", "Pronestyl", "Propafenone",
				"Proparacaine Hydrochloride", "Propecia", "Propine", "Propofol", "Propoxyphene",
				"Propoxyphene Napsylate", "Propranolol", "Propranolol Hydrochloride", "Propulsid", "Propylthiouracil",
				"Proquin XR", "Proscar", "Prosom", "Prostigmin", "Prostin E2", "Prostin VR Pediatric", "Protamine",
				"Protamines", "Protein C Concentrate", "Protirelin", "Protonix", "Protonix I.V.", "Protopic",
				"Protriptyline Hydrochloride", "Protropin", "Proventil HFA", "Provera", "Provigil", "Provisc",
				"Provocholine", "Prozac", "Pseudoephedrine", "Pseudoephedrine / Guaifenesin", "Psorcon E",
				"Pulmicort Flexhaler", "Pulmozyme", "Purinethol", "Pylera", "Pyrazinamide", "Pyridium",
				"Pyridostigmine", "Pyrimethamine", "Quadramet", "Qualaquin", "Quazepam", "Questran",
				"Quetiapine Fumarate", "Quinapril Hydrochloride", "Quinidex", "Quinidine", "Quinidine Gluconate",
				"Quinidine Sulfate", "Quinupristin and Dalfopristin", "Quixin", "Qutenza", "Qvar", "R-Gene 10",
				"Rabavert", "Rabeprazole Sodium", "Rabies Immune Globulin", "Rabies Vaccine", "Radiogardase",
				"Raloxifene", "Raltegravir", "Ramelteon", "Ramipril", "Ranexa", "Ranibizumab",
				"Ranitidine Bismuth Citrate", "Ranitidine Hcl", "Ranitidine Hydrochloride", "Ranolazine", "Rapaflo",
				"Rapamune", "Raplon", "Raptiva", "Rasagiline", "Rasburicase", "Rattlesnake Antivenin", "Raxar",
				"Razadyne ER", "Rebetol", "Rebetron", "Rebif", "Reclast", "Recombinate", "Refacto", "Refludan",
				"Regadenoson", "Reglan", "Regranex", "Relafen", "Relenza", "Relistor", "Relpax", "Remeron", "Remicade",
				"Remifentanil", "Remodulin", "Renagel", "Renese", "Renova", "Renvela", "ReoPro", "Repaglinide",
				"Repronex", "Requip", "Rescriptor", "Rescula", "Restasis", "Restoril", "Retapamulin", "Retavase",
				"Reteplase", "Retin-A Micro", "Retisert", "Retrovir", "Revatio", "Revex", "Revia", "Revlimid",
				"Reyataz", "Rezulin", "Rheumatrex", "Rhinocort Aqua", "Rhogam", "Rhogam Ultra-Filtered Plus",
				"Rhophylac", "RiaSTAP", "Ribavirin", "Rifabutin", "Rifadin", "Rifamate", "Rifampin",
				"Rifampin and Isoniazid", "Rifapentine", "Rifater", "Rifaximin", "Rilonacept", "Rilutek", "Riluzole",
				"Rimantadine", "Rimexolone", "Rimso-50", "Ringer's Solution", "Riomet", "Risedronate Sodium",
				"Risperdal", "Risperdal Consta", "Risperidone", "Ritalin", "Ritonavir", "Rituxan", "Rituximab",
				"Rivastigmine Tartrate", "Rizatriptan Benzoate", "Robaxin", "Robaxisal", "Robinul", "Robitussin Ac",
				"Rocaltrol", "Rocephin", "Rocuronium Bromide", "Rofecoxib", "Roferon-A", "Romazicon", "Romidepsin",
				"Romiplostim", "Rondec", "Ropinirole", "Ropinirole Hcl", "Ropivacaine Hcl", "Rosanil",
				"Rosiglitazone Maleate", "Rosuvastatin Calcium", "Rotarix", "RotaTeq", "Rotavirus Vaccine, Live, Oral",
				"Rotigotine", "Rowasa", "Roxanol", "Roxicet", "Roxicodone", "Rozerem", "Rubella Vaccine", "Rufinamide",
				"Rythmol", "Rythmol SR", "Ryzolt", "Sabril", "Sacrosidase", "Saizen", "Salagen", "Salmeterol Xinafoate",
				"Salsalate", "Samarium SM 153 Lexidronam", "Samsca", "Sanctura", "Sanctura XR", "Sancuso", "Sandimmune",
				"Sandostatin", "Sandostatin LAR", "Sansert", "Santyl", "Saphris", "Saquinavir Mesylate", "Sarafem",
				"Sargramostim", "Savella", "Saxagliptin", "Scopolamine", "Seasonale", "Seasonique",
				"Secobarbital Sodium", "Seconal Sodium", "SecreFlo", "Secretin", "Sectral", "Selegiline Hcl",
				"Selegiline Hydrochloride", "Selegiline Transdermal System", "Selenium", "Selsun", "Selzentry",
				"Semprex D", "Sensipar", "Sensorcaine", "Septocaine", "Septra", "Serax", "Serevent Diskus",
				"Sermorelin", "Sermorelin Acetate", "Seroquel", "Seroquel XR", "Serostim", "Serostim LQ",
				"Sertaconazole Nitrate", "Sertraline Hcl", "Serzone", "Sevelamer Carbonate", "Sevelamer Hcl",
				"Sevoflurane", "Sibutramine Hydrochloride Monohydrate", "Sildenafil Citrate", "Silenor", "Silodosin",
				"Silvadene", "Silver Sulfadiazine", "Simcor", "Simponi", "Simulect", "Simvastatin", "Sincalide",
				"Sinemet", "Sinemet CR", "Sinequan", "Singulair", "Sirolimus", "Sitagliptin Metformin HCL",
				"Sitagliptin Phosphate", "Skelaxin", "Skelid", "Slo-phyllin", "Slow-K", "Sodium Acetate",
				"Sodium Bicarbonate", "Sodium ferric gluconate", "Sodium Fluoride", "Sodium Hyaluronate",
				"Sodium Iodide I 131", "Sodium Lactate", "Sodium Oxybate", "Sodium Phenylbutyrate",
				"Sodium Phosphate Monobasic", "Sodium Polystyrene Sulfonate", "Sodium Sulfacetamide", "Solage",
				"Solaraze", "Solifenacin Succinate", "Soliris", "Solodyn", "Soltamox", "Solu Cortef", "Solu Medrol",
				"Soma", "Soma Compound", "Somatrem", "Somatropin", "Somavert", "Sonata", "Sorafenib", "Soriatane",
				"Sotalol", "Sotalol Hcl", "Sotradecol", "Sparfloxacin", "Spectazole", "Spectinomycin", "Spectracef",
				"Spiriva", "Spironolactone", "Spironolactone and Hydrochlorothiazide", "Sporanox",
				"Sporanox Oral Solution", "Sprintec", "Sprix", "Sprycel", "Stadol", "Stalevo", "Starlix", "Staticin",
				"Stavudine", "Stavzor", "Staxyn", "Stelara", "Stelazine", "Stimate", "Strattera", "Streptase",
				"Streptokinase", "Streptomycin", "Streptozocin", "Striant", "Stromectol", "Strontium-89", "Suboxone",
				"Succimer", "Succinylcholine Chloride", "Sucraid", "Sucralfate", "Sudafed", "Sufenta",
				"Sufentanil Citrate", "Sular", "Sulconazole", "Sulfacetamide", "Sulfadoxine / Pyrimethamine",
				"Sulfamethoxazole", "Sulfamylon", "Sulfasalazine", "Sulfinpyrazone", "Sulindac", "Sultrin",
				"Sumatriptan", "Sumavel DosePro", "Sumycin", "Sunitinib Malate", "Supprelin LA", "Suprane", "Suprax",
				"Surmontil", "Survanta", "Sustiva", "Sutent", "Symbicort", "Symbyax", "Symlin", "Symmetrel", "Synagis",
				"Synalar", "Synarel", "Synera", "Synercid", "Synthroid", "Synvisc", "Syprine", "Tabloid", "Taclonex",
				"Taclonex Scalp", "Tacrine", "Tacrolimus", "Tadalafil", "Tagamet", "Talacen", "Talwin", "Talwin Nx",
				"Tambocor", "Tamiflu", "Tamoxifen Citrate", "Tamsulosin Hydrochloride", "Tao", "Tapazole", "Tapentadol",
				"Tarceva", "Targretin", "Tarka", "Tasigna", "Tasmar", "Taxol", "Taxotere", "Tazarotene", "Tazorac",
				"Technetium Tc 99m", "Tegaserod Maleate", "Tegretol", "Tekturna", "Tekturna HCT", "Telavancin",
				"Telbivudine", "Telithromycin", "Telmisartan", "Telmisartan and Hydrochlorothiazide", "Temazepam",
				"Temodar", "Temovate", "Temovate Scalp", "Temozolomide", "Temsirolimus", "Tenecteplase", "Teniposide",
				"Tenofovir Disoproxil Fumarate", "Tenoretic", "Tenormin", "Tenuate", "Terazol 3, Terazol 7",
				"Terazosin Hcl", "Terbinafine", "Terbinafine Hydrochloride", "Terbutaline Sulfate", "Terconazole",
				"Teriparatide", "Terra-Cortril", "Terramycin", "Teslac", "Teslascan", "Tessalon", "Testim", "Testoderm",
				"Testolactone", "Testosterone", "Testred", "Tetanus Vaccine", "Tetrabenazine", "Tetracycline",
				"Tev-Tropin", "Teveten", "Teveten HCT", "Thalidomide", "Thalitone", "Thalomid", "Theo-24", "Theolair",
				"Theophylline", "Theophylline Anhydrous", "Theracys", "Thiabendazole", "Thiethylperazine",
				"Thioguanine", "Thiopental Sodium", "Thioridazine", "Thioridazine HCl", "Thiotepa", "Thiothixene Hcl",
				"Thorazine", "Thrombin", "Thymalfasin", "Thyrel Trh", "Thyro-Tabs", "Thyrogen", "Thyroid tablets",
				"Thyrolar", "Thyrotropin Alfa", "Tiagabine Hydrochloride", "Tiazac", "Ticarcillin", "Ticlid",
				"Ticlopidine Hcl", "Tigan", "Tigecycline", "Tikosyn", "Tilade", "Timentin", "Timolide", "Timolol",
				"Timolol Maleate", "Timoptic", "Tindamax", "Tinidazole", "Tinzaparin", "Tioconazole",
				"Tiotropium Bromide", "Tipranavir", "Tirofiban", "Tirosint", "Tizanidine", "Tnkase", "Tobi", "Tobradex",
				"Tobramycin", "Tobrex", "Tocainide HCl", "Tocilizumab", "Tofranil", "Tofranil-PM", "Tolazamide",
				"Tolcapone", "Tolectin", "Tolinase", "Tolmetin Sodium", "Tolterodine Tartrate", "Tolvaptan", "Tonocard",
				"Topamax", "Topicort", "Topiramate", "Topotecan", "Topotecan Hydrochloride", "Toprol XL", "Toradol",
				"Torecan", "Toremifene", "Torisel", "Torsemide", "Tositumomab I-131", "Totect", "Toviaz", "Tracleer",
				"Tracrium", "Tramadol", "Tramadol Hcl", "Trandate", "Trandolapril", "Trandolapril and Verapamil",
				"Tranexamic Acid", "Transderm Nitro", "Transderm Scop", "Tranxene", "Tranylcypromine", "Trastuzumab",
				"Trasylol", "Travasol", "Travatan", "Travoprost", "Trazodone Hydrochloride", "Treanda", "Trecator",
				"Trelstar", "Trental", "Treprostinil", "Treprostinil Sodium", "Tretinoin", "Trexall", "Treximet",
				"Tri-Luma", "Tri-Sprintec", "Triamcinolone", "Triamcinolone Acetonide", "Triamterene", "Triazolam",
				"Tribenzor", "Tricor", "Tridione", "Trientine", "Triesence", "Trifluoperazine", "Trifluridine",
				"Triglide", "Trihexyphenidyl", "Trileptal", "Trilipix", "Trilisate", "TriLyte", "Trimethadione",
				"Trimethobenzamide Hydrochloride", "Trimethoprim", "Trimethoprim and Sulfamethoxazole",
				"Trimetrexate Glucuronate", "Trimipramine", "TriNessa", "Triptorelin pamoate", "Trisenox", "Tritec",
				"Trivaris", "Trivora-28", "Trizivir", "Trobicin", "Troleandomycin", "TrophAmine", "Trospium",
				"Trovafloxacin", "Trovan", "Trusopt", "Truvada", "Trypan Blue", "Tubersol", "Tussionex", "Twinrix",
				"Twynsta", "Tygacil", "Tykerb", "Tylenol", "Tylenol with codeine", "Tylox", "Typhoid Vaccine",
				"Tysabri", "Tyvaso", "Tyzeka", "Ulesfia", "Uloric", "Ultane", "Ultiva", "Ultracet", "Ultram",
				"Ultram ER", "Ultrase", "Ultravate", "Ultravist", "Unasyn", "Uniphyl", "Uniretic", "Unithroid",
				"Univasc", "Unoprostone isopropyl", "Urex", "Urispas", "Urobiotic", "Urofollitropin", "Urokinase",
				"Uroxatral", "Urso", "Ursodiol", "Ustekinumab", "Uvadex", "Vaccinia Immune Globulin", "Vagifem",
				"Vagistat-1", "Valacyclovir Hydrochloride", "Valcyte", "Valdecoxib", "Valganciclovir Hcl", "Valium",
				"Valproate Sodium", "Valproic Acid", "Valrubicin", "Valsartan", "Valsartan and Hydrochlorothiazide",
				"Valstar", "Valtrex", "Valtropin", "Valturna", "Vancomycin Hydrochloride", "Vaniqa", "Vanos", "Vantin",
				"Vaprisol", "Vaqta", "Vardenafil HCl", "Varenicline", "Varicella Virus Vaccine Live", "Varivax",
				"Vascor", "Vaseretic", "Vasocidin", "Vasopressin", "Vasotec", "Vasovist", "Vectibix", "Vectical",
				"Velcade", "Velosulin", "Veltin", "Venlafaxine Hydrochloride", "Venofer", "Ventavis", "Ventolin HFA",
				"VePesid", "Veramyst", "Verapamil", "Verapamil HCl", "Verapamil Hydrochloride", "Verdeso", "Veregen",
				"Verelan PM", "Vermox", "Verteporfin", "Vesanoid", "VESIcare", "Vexol", "Vfend", "Viadur", "Viagra",
				"Vibativ", "Vibramycin", "Vibramycin Intravenous", "Vicodin", "Vicodin ES", "Vicodin HP", "Vicoprofen",
				"Victoza", "Vidarabine", "Vidaza", "Videx", "Videx EC", "Vigabatrin", "Vigamox", "Vimovo", "Vimpat",
				"Vinblastine Sulfate", "Vincasar PFS", "Vincristine Sulfate", "Vinorelbine Tartrate", "Viokase",
				"Vioxx", "Vira-A", "Viracept", "Viramune", "Virazole", "Viread", "Viroptic", "Visicol", "VisionBlue",
				"Visken", "Vistaril", "Vistide", "Visudyne", "Vitamin A", "Vitamin C", "Vitamin K1", "Vitrasert",
				"Vitravene", "Vivactil", "Vivaglobin", "Vivelle-Dot", "Vivitrol", "Voltaren", "Voltaren Ophthalmic",
				"Voluven", "Voriconazole", "Vorinostat", "Vosol Hc", "VoSpire ER", "Votrient", "Vumon", "Vusion",
				"Vytorin", "Vyvanse", "Warfarin Sodium", "Welchol", "Wellbutrin", "Wellbutrin SR", "Wellbutrin XL",
				"Westcort", "Wigraine", "Winstrol", "Xalatan", "Xanax", "Xanax XR", "Xeloda", "Xenazine", "Xenical",
				"Xeomin", "Xerese", "Xibrom", "Xifaxan", "Xigris", "Xolair", "Xolegel", "Xopenex", "Xopenex HFA",
				"Xylocaine", "Xylocaine Viscous", "Xyntha", "Xyrem", "Xyzal", "Yasmin", "Yaz", "Yellow Fever Vaccine",
				"Yf-Vax", "Yohimbine", "Yohimbine Hydrochloride", "Zadaxin", "Zaditor", "Zafirlukast", "Zagam",
				"Zalcitabine", "Zaleplon", "Zanaflex", "Zanamivir", "Zanosar", "Zantac", "Zarontin", "Zavesca",
				"Zebeta", "Zegerid", "Zelapar", "Zelnorm", "Zemaira", "Zemplar", "Zemuron", "Zenapax", "Zenpep",
				"Zerit", "Zestoretic", "Zestril", "Zetia", "Zevalin", "Ziac", "Ziagen", "Ziana", "Ziconotide",
				"Zidovudine", "Zinacef", "Zinc", "Zinecard", "Zingo", "Ziprasidone", "Zirgan", "Zithromax", "Zmax",
				"Zn-DTPA", "Zocor", "Zofran", "Zoledronic Acid", "Zolinza", "Zolmitriptan", "Zoloft",
				"Zolpidem Tartrate", "Zolpimist", "Zometa", "Zomig", "Zonalon", "Zonegran", "Zonisamide", "Zortress",
				"Zostavax", "Zoster Vaccine Live", "Zosyn", "Zovia", "Zovirax", "Zyban", "Zyclara", "Zydone", "Zyflo",
				"Zyflo CR", "Zylet", "Zyloprim", "Zymar", "Zymaxid", "Zyprexa", "Zyprexa Relprevv", "Zyrtec",
				"Zyrtec-D", "Zyvox" };

		// formatting

		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].replaceAll("[^\\sa-zA-Z0-9]", "");
			names[i] = names[i].replace(" ", "+").toLowerCase();
			
		}

		// getting IDs
		String result;
		int ID = 0;
		URL url;
		BufferedReader br;
		BufferedWriter writer = new BufferedWriter(new FileWriter("drugIDs.txt"));

		for (int i = 0; i < names.length; i++) {
			url = new URL("https://rxnav.nlm.nih.gov/REST/rxcui.json?name=" + names[i] + "&search=2");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			while ((result = br.readLine()) != null) {
				try {
					ID = Integer.parseInt(result.substring(result.indexOf(":[") + 3, result.indexOf("]") - 1));
				} catch (StringIndexOutOfBoundsException e) {
					ID = 0;
				} catch (NumberFormatException e) {
					ID = Integer.parseInt(result.substring(result.indexOf(":[") + 3, result.indexOf(",") - 1));
				}
			}
			writer.write(names[i] + " " + ID + "\n");
			System.out.println("Added " + names[i] + " to file.");
			conn.disconnect();
			Thread.sleep(50);
		}
		writer.close();
	}
	
	private static void listCleanup() throws IOException {
		// TODO Auto-generated method stub
				BufferedWriter writer = new BufferedWriter(new FileWriter("drugRCXUIs.txt"));
				BufferedReader reader = new BufferedReader(new FileReader("drugIDs.txt"));
				String imported = " ";
				
				
				while (true) {
					imported = reader.readLine();
					if (imported == null) break;
					if (imported.substring(imported.indexOf(" ")+1, imported.indexOf(" ")+2).compareTo("0") != 0)  {
						writer.write(imported + "\n");
					}
				}
				writer.close();
				System.out.println("List Cleaned");
	}
	
	private static void scraper() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
				BufferedReader reader = new BufferedReader(new FileReader("drugRCXUIs.txt"));
				BufferedReader br;
				BufferedWriter writer;

				String imported = "";
				String name = "";
				int drugID = 0;
				URL url;
				String result;

				while (true) {
					Thread.sleep(50);
					imported = reader.readLine();
					name = imported.substring(0, imported.indexOf(" "));
					System.out.println(imported);
					
					if (imported == null) {
						System.out.println("Finished");
						break;
					} else {
						drugID = Integer.parseInt(imported.substring(imported.indexOf(" ") + 1));
						
						writer = new BufferedWriter(new FileWriter("drugInteractions/" + name + ".xml"));
						
						url = new URL("https://rxnav.nlm.nih.gov/REST/interaction/interaction?rxcui=" + drugID);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/xml");
						
						br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
						
						while ((result = br.readLine()) != null) {
							writer.write(result + "\n");
						}
						writer.close();
						System.out.println("Data for " + name + " has been scraped.");
					}
				}
				reader.close();
	}
}
