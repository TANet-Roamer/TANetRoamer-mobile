/**
 * @flow
 */

import last from 'lodash/last'

const syncNavigator = () => {
  let navigator: ?Object = null
  const setNavigator = (nativeNavigator: Object) => {
    navigator = nativeNavigator
  }

  const middleware = (store: Object) => (next: Function) => (action: Object) => {
    if (navigator && action.type.startsWith('NAV/')) {
      const navAction = action.type.slice(4).toLowerCase()
      navigator[navAction](action.payload)
      if (navAction === 'pop') {
        action.payload = last(navigator.getCurrentRoutes())
      }
    }

    return next(action)
  }

  return {
    setNavigator,
    middleware
  }
}

export default syncNavigator()
