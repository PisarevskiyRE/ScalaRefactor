package chapter_5

object Task_21 extends App{
/*
  trait Trainer {
    val level: String

    def practice(): Unit
  }

  class BeginnerTrainer extends Trainer {
    override val level: String = "beginner"

    override def practice(): Unit = {
      println(s"practicing $level task")
    }

  }

  trait TrainerInitializer {
    def initializeTrainer(): Trainer
  }

  object BeginnerTrainerInitializer extends TrainerInitializer {
    override def initializeTrainer(): Trainer = new BeginnerTrainer
  }


  class LanguageTrainerService(trainerInitializer: TrainerInitializer) {
    def practice(): Unit = {
      trainerInitializer
        .initializeTrainer()
        .practice()
    }

  }


  sealed trait TaskGenerator {
    def generateTask(): String
  }

  case object BeginnerTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"beginner level task"
  }

  case object IntermediateTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"intermediate level task"
  }

  case object AdvancedTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"advanced level task"
  }


  class LanguageTrainer() {
    def practice(level: String): Unit = {
      val task = {
        if (level == "beginner") BeginnerTaskGenerator.generateTask()
        else if (level == "intermediate") IntermediateTaskGenerator.generateTask()
        else if (level == "advanced") AdvancedTaskGenerator.generateTask()
        else throw new IllegalArgumentException("Unknown Level")
      }

      println(s"practicing $level $task ")
    }
  }
*/

  sealed trait TaskGenerator {
    def generateTask(): String
  }

  class BeginnerTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"beginner level task"
  }

  class IntermediateTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"intermediate level task"
  }

  class AdvancedTaskGenerator extends TaskGenerator {
    override def generateTask(): String = s"advanced level task"
  }

  trait Trainer {
    val level: String

    def practice(): Unit
  }

  class BeginnerTrainer extends Trainer {
    override val level: String = "beginner"

    override def practice(): Unit = {
      val taskGenerator = new BeginnerTaskGenerator()
      val task = taskGenerator.generateTask()
      println(s"practicing $level $task")
    }
  }

  trait TrainerInitializer {
    def initializeTrainer(): Trainer
  }

  object BeginnerTrainerInitializer extends TrainerInitializer {
    override def initializeTrainer(): Trainer = new BeginnerTrainer
  }

  class LanguageTrainerService(trainerInitializer: TrainerInitializer) {
    def practice(): Unit = {
      val trainer = trainerInitializer.initializeTrainer()
      trainer.practice()
    }
  }
}
