/* @flow */

class NavigatorStack {
  register(name: string, component: ReactClass<*>, info: Object = {}) {
    this.componentMap[name] = {
      component,
      info
    }
  }

  getRoute(name: string) {
    const { component, info } = this.componentMap[name]
    return Object.assign({}, info, { name, component })
  }

  componentMap = {}
}

const stack = new NavigatorStack()

export default stack
