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




trait Trainer {
  val level: String

  def practice(): Unit
}

class BeginnerTrainer extends Trainer{
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

trait TaskInitializer {
  def initializeTask(): TaskGenerator
}

object BeginnerTaskInitializer extends TaskInitializer {
  override def initializeTask(): TaskGenerator = BeginnerTaskGenerator
}





class LanguageTrainerService(trainerInitializer: TrainerInitializer) {
  def practice(): Unit = {
    trainerInitializer
      .initializeTrainer()
      .practice()
  }

}


