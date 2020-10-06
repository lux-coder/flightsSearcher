import { AdditionalServices } from './additional-services';

export class Price {
    currency: string;
    total: string;
    base: string;
    additionalServices: AdditionalServices[];
}
