package simbl.service

class DuplicateUsernameException(username: String)
extends Exception(s"user with username '$username' already exists")
