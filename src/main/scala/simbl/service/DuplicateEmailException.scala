package simbl.service

class DuplicateEmailException(email: String)
extends Exception(s"user with email '$email' already exists")
