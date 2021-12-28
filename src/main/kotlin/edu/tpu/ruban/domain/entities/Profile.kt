package edu.tpu.ruban.domain.entities

data class Profile(
    val userId: Long,
    val isAdmin: Boolean,
    val isBanned: Boolean
) {
}