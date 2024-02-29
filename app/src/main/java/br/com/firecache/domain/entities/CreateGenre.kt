package br.com.firecache.domain.entities

data class CreateGenre(
    val name: String
){
    constructor(genre: Genre): this(genre.name)
}