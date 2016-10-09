/**
 * @flow
 */

import { takeEvery } from 'redux-saga'
import { put } from 'redux-saga/effects'
import { STORE_ACCOUNT, FETCH_ACCOUNT, CLEAR_ACCOUNT } from '../constants/wifi-account'
import { setAccount } from '../actions/wifi-account'
import { pop } from '../actions/nav'
import WifiAccountStorage from '../WifiAccountStorage'

export function* fetchAccount(): Iterable<Object> {
  const info = yield WifiAccountStorage.getLoginInfo()
  yield put(setAccount(info))
}

type StorePayload = {
  payload: Object
}

export function* storeAccount({ payload }: StorePayload): Iterable<Object> {
  yield WifiAccountStorage.setLoginInfo(payload.username, payload.password)
  yield put(setAccount(payload))
  yield put(pop())
}

export function* clearAccount({ payload }: StorePayload): Iterable<Object> {
  yield WifiAccountStorage.clearLoginInfo()
  yield put(setAccount({
    username: null,
    password: null
  }))
}

export function* watcher(): Iterable<[]> {
  yield [
    takeEvery(FETCH_ACCOUNT, fetchAccount),
    takeEvery(STORE_ACCOUNT, storeAccount),
    takeEvery(CLEAR_ACCOUNT, clearAccount)
  ]
}

export default watcher
