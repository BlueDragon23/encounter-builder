// https://stackoverflow.com/a/72075415
export type RequiredFields<T, K extends keyof T> = T & Required<Pick<T, K>>;
