/**
 * @flow
 */

import { createAction } from 'redux-actions'
import { PUSH, POP } from '../constants/nav'

export const push = createAction(PUSH)
export const pop = createAction(POP)
