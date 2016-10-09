declare module WifiAccountStorage {
  declare function getLoginInfo(): Promise<Object>
  declare function setLoginInfo(username: string, password: string): Promise<null>
  declare function clearLoginInfo(): Promise<null>
}
