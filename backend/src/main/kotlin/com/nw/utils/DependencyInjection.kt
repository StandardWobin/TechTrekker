package com.nw.utils

import com.nw.facade.FilterFacade
import com.nw.facade.TechnologyFacade
import com.nw.facade.UserFacade
import com.nw.persistence.FilterRepository
import com.nw.persistence.TechnologyRepository
import com.nw.persistence.UserRepository

val userFacade: UserFacade = UserRepository()
val filterFacade: FilterFacade = FilterRepository()
val technologyFacade: TechnologyFacade = TechnologyRepository()
