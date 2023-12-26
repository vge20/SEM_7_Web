export function genderToString(gender: boolean) {
    if (gender) {
        return 'М';
    }

    return 'Ж';
}

export function stringToGender(str: string) {
    if (str === 'Ж') {
        return false;
    }

    return true;
}