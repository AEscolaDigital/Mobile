package com.example.school.models

import java.util.*

class Task(
    var id: Int? = null,
    var title: String,
    var description: String,
    var deliveryDate: Date? = null,
    var punctuation: Int? = null,
    var attachment: String
)