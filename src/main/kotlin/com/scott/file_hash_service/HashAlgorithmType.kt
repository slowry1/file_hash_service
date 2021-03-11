package com.scott.file_hash_service

enum class HashAlgorithmType(val algorithmType: String) {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA224("SHA-224"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512"),
    SHA512T("SHA-512t")
//    "SHA-512/224" look into these since they are a member of the sha-2 family. it doesnt look like bouncy castle has them.
//    "SHA-512/256"
}

