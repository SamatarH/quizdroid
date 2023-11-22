package edu.uw.ischool.samatar.quizdroid

//class QuizRepository {
//    private val mathQuestions = listOf(
//        Question("What is the sum of 2 + 2?", listOf("3", "4", "5", "6"), "4"),
//        Question("What is the product of 3 × 7?", listOf("18", "21", "24", "27"), "21"),
//        Question("What is the result of 10 ÷ 2?", listOf("2", "4", "5", "6"), "4"),
//        Question("What is the square root of 25?", listOf("3", "4", "5", "6"), "5"),
//        Question("What is 3²?", listOf("6", "9", "12", "15"), "9")
//    )
//
//    private val physicsQuestions = listOf(
//        Question("What is the SI unit of force?", listOf("Newton", "Joule", "Watt", "Ampere"), "Newton"),
//        Question("What is the speed of light in a vacuum?", listOf("300,000 km/s", "200,000 km/s", "400,000 km/s", "500,000 km/s"), "300,000 km/s"),
//        Question("What is the formula for calculating momentum?", listOf("P = mv/t", "P = mv^2/2", "P = m/v", "P = mv"), "P = mv"),
//        Question("What is the first law of thermodynamics also known as?", listOf("Law of Entropy", "Law of Conservation of Energy", "Boyle's Law", "Archimedes' Principle"), "Law of Conservation of Energy"),
//        Question("What is the acceleration due to gravity on Earth's surface?", listOf("9.8 m/s²", "8.2 m/s²", "10.2 m/s²", "7.6 m/s²"), "9.8 m/s²")
//    )
//
//    private val marvelQuestions = listOf(
//        Question("What is the real name of Iron Man?", listOf("Tony Stark", "Bruce Wayne", "Peter Parker", "Clark Kent"), "Tony Stark"),
//        Question("Which superhero wields Mjolnir, the enchanted hammer?", listOf("Iron Man", "Thor", "Captain America", "Hulk"), "Thor"),
//        Question("What is the name of the metal used for Wolverine's claws?", listOf("Adamantium", "Vibranium", "Kryptonite", "Titanium"), "Adamantium"),
//        Question("What is the alias of Natasha Romanoff in Marvel comics?", listOf("Wonder Woman", "Black Widow", "Scarlet Witch", "Rogue"), "Black Widow"),
//        Question("Who is the arch-nemesis of Spider-Man?", listOf("Green Goblin", "Magneto", "Loki", "Doctor Doom"), "Green Goblin")
//    )
//
//    fun getQuestions(topic: String): List<Question> {
//        return when (topic) {
//            "math" -> mathQuestions
//            "physics" -> physicsQuestions
//            "marvel" -> marvelQuestions
//            else -> emptyList() // Return an empty list for unknown topics
//        }
//    }
//}
