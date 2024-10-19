package com.ebrahimamin.weather.api_files

data class Location(
    val location: String,  // Latitude and Longitude in the form "q=48.8567,2.3508"
    val cityName: String
)

val topCities = listOf(





    Location("48.8567,2.3508", "Paris"),                 // Paris, France
    Location("40.7128,-74.0060", "New York"),            // New York, USA
    Location("35.6895,139.6917", "Tokyo"),               // Tokyo, Japan
    Location("51.5074,-0.1278", "London"),               // London, UK
    Location("-33.8688,151.2093", "Sydney"),             // Sydney, Australia
    Location("55.7558,37.6173", "Moscow"),               // Moscow, Russia
    Location("34.0522,-118.2437", "Los Angeles"),        // Los Angeles, USA
    Location("19.4326,-99.1332", "Mexico City"),         // Mexico City, Mexico
    Location("-23.5505,-46.6333", "São Paulo"),          // São Paulo, Brazil
    Location("28.6139,77.2090", "New Delhi"),            // New Delhi, India
    Location("39.9042,116.4074", "Beijing"),             // Beijing, China
    Location("41.9028,12.4964", "Rome"),                 // Rome, Italy
    Location("37.7749,-122.4194", "San Francisco"),      // San Francisco, USA
    Location("52.5200,13.4050", "Berlin"),               // Berlin, Germany
    Location("31.2304,121.4737", "Shanghai"),            // Shanghai, China
    Location("1.3521,103.8198", "Singapore"),            // Singapore
    Location("34.6937,135.5023", "Osaka"),               // Osaka, Japan
    Location("40.4168,-3.7038", "Madrid"),               // Madrid, Spain
    Location("19.0760,72.8777", "Mumbai"),               // Mumbai, India
    Location("37.5665,126.9780", "Seoul"),               // Seoul, South Korea
    Location("6.5244,3.3792", "Lagos"),                  // Lagos, Nigeria
    Location("-34.6037,-58.3816", "Buenos Aires"),       // Buenos Aires, Argentina
    Location("-22.9068,-43.1729", "Rio de Janeiro"),     // Rio de Janeiro, Brazil
    Location("30.0444,31.2357", "Cairo"),                // Cairo, Egypt
    Location("31.7683,35.2137", "Jerusalem"),            // Jerusalem, Israel
    Location("-26.2041,28.0473", "Johannesburg"),        // Johannesburg, South Africa
    Location("55.8642,-4.2518", "Glasgow"),              // Glasgow, UK
    Location("35.2271,-80.8431", "Charlotte"),           // Charlotte, USA
    Location("18.5204,73.8567", "Pune"),                 // Pune, India
    Location("23.1291,113.2644", "Guangzhou"),           // Guangzhou, China
    Location("14.5995,120.9842", "Manila"),              // Manila, Philippines
    Location("13.7563,100.5018", "Bangkok"),             // Bangkok, Thailand
    Location("22.3964,114.1095", "Hong Kong"),           // Hong Kong, China

    // Egypt
    Location("31.2156,29.9553", "Alexandria"),           // Alexandria, Egypt
    Location("30.5847,31.5017", "Zagazig"),              // Zagazig, Egypt
    Location("30.0647,31.2756", "Giza"),                 // Giza, Egypt
    Location("30.5566,31.6783", "Shibin El Kom"),        // Shibin El Kom, Egypt
    Location("31.2001,31.1804", "Damietta"),             // Damietta, Egypt
    Location("31.1236,33.8007", "Port Said"),            // Port Said, Egypt
    Location("30.8418,30.9653", "Tanta"),                // Tanta, Egypt
    Location("26.8206,31.6199", "Sohag"),                // Sohag, Egypt
    Location("30.4667,31.1833", "Banha"),                // Banha, Egypt
    Location("28.1099,30.7500", "Minya"),                // Minya, Egypt
    Location("27.1809,31.1837", "Asyut"),                // Asyut, Egypt
    Location("25.6872,32.6396", "Luxor"),                // Luxor, Egypt
    Location("27.2579,33.8116", "Hurghada"),             // Hurghada, Egypt
    Location("24.0889,32.8998", "Aswan"),                // Aswan, Egypt
    Location("30.8138,31.0625", "Damanhur"),             // Damanhur, Egypt
    Location("30.8307,30.9903", "Kafr El Sheikh"),       // Kafr El Sheikh, Egypt
    Location("27.1333,31.2333", "New Valley"),           // New Valley, Egypt
    Location("30.8232,31.3562", "Menouf"),               // Menouf, Egypt
    Location("30.4068,31.1686", "Qalyubia"),             // Qalyubia, Egypt
    Location("29.9668,32.5485", "Suez"),                 // Suez, Egypt
    Location("31.0482,30.4689", "Beheira"),              // Beheira, Egypt
    Location("29.8414,31.3050", "Helwan"),               // Helwan, Egypt
    Location("30.5511,31.5020", "Belqas"),               // Belqas, Egypt
    Location("31.1310,30.6466", "Rosetta"),              // Rosetta (Rashid), Egypt
    Location("31.6165,30.7562", "Kafr El Dawwar"),       // Kafr El Dawwar, Egypt
    Location("26.5421,31.6951", "Qena"),                 // Qena, Egypt
    Location("31.1975,31.4554", "Mansoura"),             // Mansoura, Egypt
    Location("31.0461,31.3785", "Mit Ghamr"),            // Mit Ghamr, Egypt
    Location("29.9039,31.2001", "6th of October City"),  // 6th of October City, Egypt
    Location("31.0289,31.4184", "Talkha"),               // Talkha, Egypt
    Location("31.1946,31.2812", "Mahalla El Kubra"),     // Mahalla El Kubra, Egypt
    Location("31.3509,30.8221", "Edku"),                 // Edku, Egypt
    Location("28.7911,30.6993", "Beni Suef"),            // Beni Suef, Egypt
    Location("31.0409,31.3789", "Zefta"),                // Zefta, Egypt
    Location("27.6553,30.7058", "El Kharga"),            // El Kharga, Egypt
    Location("30.9057,31.2584", "Ashmoun"),              // Ashmoun, Egypt
    Location("30.3081,31.4646", "Belbeis"),              // Belbeis, Egypt
    Location("29.3032,30.8400", "Faiyum"),               // Faiyum, Egypt
    Location("30.9500,32.2667", "Baltim"),               // Baltim, Egypt
    Location("31.0613,31.3977", "Senbellawein"),         // Senbellawein, Egypt




    Location("30.2672,-97.7431", "Austin"),              // Austin, USA
    Location("43.6511,-79.3835", "Toronto"),             // Toronto, Canada
    Location("-37.8136,144.9631", "Melbourne"),          // Melbourne, Australia
    Location("50.1109,8.6821", "Frankfurt"),             // Frankfurt, Germany
    Location("50.0755,14.4378", "Prague"),               // Prague, Czech Republic
    Location("41.3851,2.1734", "Barcelona"),             // Barcelona, Spain
    Location("23.8103,90.4125", "Dhaka"),                // Dhaka, Bangladesh
    Location("3.1390,101.6869", "Kuala Lumpur"),         // Kuala Lumpur, Malaysia
    Location("45.4642,9.1900", "Milan"),                 // Milan, Italy
    Location("25.2760,55.2962", "Dubai"),                // Dubai, UAE
    Location("22.5726,88.3639", "Kolkata"),              // Kolkata, India
    Location("41.0082,28.9784", "Istanbul"),             // Istanbul, Turkey
    Location("29.7604,-95.3698", "Houston"),             // Houston, USA
    Location("45.5017,-73.5673", "Montreal"),            // Montreal, Canada
    Location("-1.2921,36.8219", "Nairobi"),              // Nairobi, Kenya
    Location("12.9716,77.5946", "Bangalore"),            // Bangalore, India
    Location("52.2297,21.0122", "Warsaw"),               // Warsaw, Poland
    Location("53.3498,-6.2603", "Dublin"),               // Dublin, Ireland
    Location("60.1699,24.9384", "Helsinki"),             // Helsinki, Finland
    Location("45.4215,-75.6972", "Ottawa"),              // Ottawa, Canada
    Location("24.7136,46.6753", "Riyadh"),               // Riyadh, Saudi Arabia
    Location("41.8781,-87.6298", "Chicago"),             // Chicago, USA
    Location("35.6895,51.3890", "Tehran"),               // Tehran, Iran
    Location("39.9334,32.8597", "Ankara"),               // Ankara, Turkey
    Location("53.4808,-2.2426", "Manchester"),           // Manchester, UK
    Location("-15.7942,-47.8825", "Brasilia"),           // Brasilia, Brazil
    Location("-33.9249,18.4241", "Cape Town"),           // Cape Town, South Africa
    Location("4.7109,-74.0721", "Bogota")                // Bogota, Colombia


)
