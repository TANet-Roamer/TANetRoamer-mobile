/**
 * @flow
 */

import { createStore, applyMiddleware } from 'redux'
import createSagaMiddleware from 'redux-saga'
import reducers from './reducers'
import { middleware } from './syncNavigator'

const sagaMiddleware = createSagaMiddleware()

const store = createStore(reducers, applyMiddleware(sagaMiddleware, middleware))

store.runSaga = sagaMiddleware.run

export default store
