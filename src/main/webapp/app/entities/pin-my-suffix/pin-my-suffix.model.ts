import { BaseEntity } from './../../shared';

export class PINMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public account?: string,
        public pin?: string,
        public createip?: string,
        public createuser?: string,
        public createtimestamp?: any,
        public expiretimestamp?: any,
        public claimtimestamp?: any,
        public claimuser?: string,
        public claimip?: string,
    ) {
    }
}
