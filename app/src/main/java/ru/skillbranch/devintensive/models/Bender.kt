package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL,
             var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question){
                Question.NAME -> Question.NAME.question
                Question.PROFESSION -> Question.PROFESSION.question
                Question.MATERIAL -> Question.MATERIAL.question
                Question.BDAY -> Question.BDAY.question
                Question.SERIAL -> Question.SERIAL.question
                Question.IDLE -> Question.IDLE.question
    }
    var count = 0
    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
        return if(question.answers.contains(answer)){
            question = question.nextQuestion()
            status = Status.NORMAL
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            count++
            if(count == 3){
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("bender", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("programmer", "designer")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("What my is material?", listOf("metal", "derevo")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("What my bday?", listOf("today", "yesterday")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("Отлично - ты справился\nНа этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
    }
}