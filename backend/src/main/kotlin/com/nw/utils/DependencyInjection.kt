package com.nw.utils

import com.nw.facade.UserFacade
import com.nw.persistence.UserRepository

val userFacade: UserFacade = UserRepository()
