package com.mindorks.framework.mvvm.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {
    @Test
    fun test() {
        val id = 1
        val name = "Jame Doe"
        val email = "jame.doe@gmail.com"
        val avatar = "avatar"
        val user = User(id, name, email, avatar)

        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(email, user.email)
        assertEquals(avatar, user.avatar)
    }
}