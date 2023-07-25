package chapter_5

object Task_21 extends App{

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

  trait TaskInitializer{
    def initializeTask(): TaskGenerator
  }

  object BeginnerTaskInitializer extends TaskInitializer {
    override def initializeTask(): TaskGenerator = new BeginnerTaskGenerator()
  }

  trait Trainer {
    val level: String

    def practice(): Unit
  }

  class BeginnerTrainer(taskGenerator: TaskGenerator) extends Trainer {
    override val level: String = "beginner"

    override def practice(): Unit = {
      val task = taskGenerator.generateTask()
      println(s"practicing $level $task")
    }
  }

  trait TrainerInitializer {
    def initializeTrainer(): Trainer
  }

  object BeginnerTrainerInitializer extends TrainerInitializer {
    override def initializeTrainer(): Trainer = new BeginnerTrainer(BeginnerTaskInitializer.initializeTask())
  }

  class LanguageTrainerService(trainerInitializer: TrainerInitializer) {
    def practice(): Unit = {
      trainerInitializer
        .initializeTrainer()
        .practice()
    }
  }
}
